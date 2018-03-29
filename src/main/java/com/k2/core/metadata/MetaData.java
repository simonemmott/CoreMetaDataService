package com.k2.core.metadata;

import java.io.Serializable;
import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.k2.K2Service.dataAccess.K2Dao;
import com.k2.K2Service.dataAccess.K2DaoFactory;
import com.k2.MetaModel.annotations.MetaService;
import com.k2.MetaModel.model.MetaModel;
import com.k2.MetaModel.model.MetaModelService;
import com.k2.MetaModel.model.MetaModelType;
import com.k2.MetaModel.model.types.MetaModelPrimitive;
import com.k2.MetaModel.model.types.classes.MetaModelNative;
import com.k2.Util.KeyUtil;
import com.k2.Util.StringUtil;
import com.k2.Util.Identity.IdentityUtil;
import com.k2.core.config.CoreMetaDataServiceConfig;
import com.k2.core.metadata.types.MetaToK2Class;
import com.k2.core.metadata.types.MetaToK2Interface;
import com.k2.core.metadata.types.MetaToK2Primitive;
import com.k2.core.metadata.types.MetaToK2SubType;
import com.k2.core.metadata.types.classes.MetaToK2Embeddable;
import com.k2.core.metadata.types.classes.MetaToK2Entity;
import com.k2.core.metadata.types.classes.MetaToK2Native;
import com.k2.core.metadata.types.classes.MetaToK2Transient;
import com.k2.core.model.*;
import com.k2.core.model.types.K2Class;
import com.k2.core.model.types.K2Interface;
import com.k2.core.model.types.K2Primitive;
import com.k2.core.model.types.K2SubType;
import com.k2.core.model.types.classes.K2Embeddable;
import com.k2.core.model.types.classes.K2Entity;
import com.k2.core.model.types.classes.K2Native;
import com.k2.core.model.types.classes.K2Transient;

public class MetaData implements K2DaoFactory {

	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	public static MetaData fromMetaModel(MetaModel metaModel) {
		return new MetaData(metaModel);
	}
	
	private MetaModel metaModel;
	public MetaModel getMetaModel() { return metaModel; }
	
	private MetaModelService coreMetaService;
	public MetaModelService getMetaServicel() { return coreMetaService; }
	
	private Map<Class<?>, ClassCache<?>> cache = new HashMap<Class<?>, ClassCache<?>>();
	@SuppressWarnings("unchecked")
	<T> ClassCache<T> getClassCache(Class<T> cls) {
		ClassCache<T> classCache = (ClassCache<T>) cache.get(cls);
		if (classCache == null) {
			classCache = new ClassCache<T>(this, cls);
			cache.put(cls, classCache);
		}
		return classCache;
	}
	<T> T find(Class<T> cls, Serializable key) {
		return getClassCache(cls).get(key);
	}
	<T> List<T> list(Class<T> cls) {
		return getClassCache(cls).list();
	}
	
	@SuppressWarnings("unchecked")
	<T> T persist(T instance) {
		if (instance == null) 
			return null;
		Class<T> cls = (Class<T>) instance.getClass();
		return getClassCache(cls).persist(instance);
	}
	
	@SuppressWarnings("unchecked")
	<T> T merge(T instance) {
		if (instance == null) 
			return null;
		Class<T> cls = (Class<T>) instance.getClass();
		return getClassCache(cls).merge(instance);
	}
	
	@SuppressWarnings("unchecked")
	<T> T remove(T instance) {
		if (instance == null) 
			return null;
		Class<T> cls = (Class<T>) instance.getClass();
		return getClassCache(cls).remove(instance);
	}
	
	private Map<Class<?>, MetaToDataConvertor<?>> convertors = new HashMap<Class<?>, MetaToDataConvertor<?>>();

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private MetaData(MetaModel metaModel) {
		
		convertors.put(K2Type.class, new MetaToK2Type(this));
		convertors.put(K2Class.class, new MetaToK2Class(this));
		convertors.put(K2Interface.class, new MetaToK2Interface(this));
		convertors.put(K2Primitive.class, new MetaToK2Primitive(this));
		convertors.put(K2SubType.class, new MetaToK2SubType(this));
		convertors.put(K2Embeddable.class, new MetaToK2Embeddable(this));
		convertors.put(K2Entity.class, new MetaToK2Entity(this));
		convertors.put(K2Native.class, new MetaToK2Native(this));
		convertors.put(K2Transient.class, new MetaToK2Transient(this));
		convertors.put(K2Service.class, new MetaToK2Service(this));

		this.metaModel = metaModel;		
		this.coreMetaService = metaModel.metaModelService(CoreMetaDataServiceConfig.class.getAnnotation(MetaService.class).alias());
		
		MetaToK2Type typeConvertor = (MetaToK2Type) convertors.get(K2Type.class);
		for (MetaModelPrimitive primitive : MetaModelPrimitive.getPrimitives()) {
			persist(typeConvertor.convert(primitive));
		}
		
		for (MetaModelNative n : MetaModelNative.getNatives()) {
			persist(typeConvertor.convert(n));
		}
		
		MetaToK2Service servicConvertor = (MetaToK2Service) convertors.get(K2Service.class);
		for (MetaModelService metaService : metaModel.implementedServices()) {
			persist(servicConvertor.convert(metaService));
		}
		
		for (MetaModelType<?> metaType : coreMetaService.getManagedTypes()) {
			persist(typeConvertor.convert(metaType));
		}
		
		while (links != null) {
			
			List<LinkedMetaData> clone = Arrays.asList(links.toArray(new LinkedMetaData[links.size()]));
			links = null;
			
			for (LinkedMetaData link : clone) {
				
				logger.trace("LinkedMetaData: {}", link);
				
				MetaToDataConvertor<?> convertor = convertors.get(link.getLinkToClass());
				Object metaObject = link.getMetaObject();
				Serializable key = convertor.getKeyFor(metaObject);
				ClassCache<?> cc = this.getClassCache(link.getLinkToClass());
				Object linkedObj = cc.get(key);
				if (linkedObj == null)
					linkedObj = convertor.convert(metaObject);
				link.setTarget(linkedObj);
			}
		}
		
		
	}
	
	private List<LinkedMetaData<?,?>> links = null;
	
	public void addLinkedMetaData(LinkedMetaData<?,?> link) {
		if (links == null)
			links = new ArrayList<LinkedMetaData<?,?>>();
		links.add(link);
		
	}
	
	@SuppressWarnings("unchecked")
	public <T> MetaToDataConvertor<T> getMetaToDataConvertor(Class<T> metaClass) {
		MetaToDataConvertor<T> convertor = (MetaToDataConvertor<T>) convertors.get(metaClass);
		if (convertor == null)
			throw new MetaDataError("No meta model to meta data convertor defined for mode class - {}", metaClass.getName());
		return convertor;
	}
	
	private Map<Class<?>, K2Dao<?,?>> daos;
	
	@Override
	public <E> K2Dao<E, ?> getDao(Class<E> type) {
		return getDao(type, KeyUtil.getKeyClass(type));
	}
	@SuppressWarnings("unchecked")
	@Override
	public <E, K extends Serializable> K2Dao<E, K> getDao(Class<E> type, Class<K> keyType) {
		if (daos == null)
			daos = new HashMap<Class<?>, K2Dao<?,?>>();
		K2Dao<E,K> dao = (K2Dao<E,K>) daos.get(type);
		if (dao == null) {
			dao = new MetaDataDao<E,K>(this, type, keyType);
			daos.put(type, dao);
		}
		return dao;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
