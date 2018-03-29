package com.k2.core.metadata.types;

import java.io.Serializable;

import com.k2.MetaModel.model.types.MetaModelClass;
import com.k2.MetaModel.model.types.classes.MetaModelEmbeddable;
import com.k2.MetaModel.model.types.classes.MetaModelEntity;
import com.k2.MetaModel.model.types.classes.MetaModelNative;
import com.k2.MetaModel.model.types.classes.MetaModelTransient;
import com.k2.core.metadata.LinkedMetaData;
import com.k2.core.metadata.MetaData;
import com.k2.core.metadata.MetaDataError;
import com.k2.core.metadata.MetaToDataConvertor;
import com.k2.core.metadata.types.classes.MetaToK2Embeddable;
import com.k2.core.metadata.types.classes.MetaToK2Entity;
import com.k2.core.metadata.types.classes.MetaToK2Native;
import com.k2.core.metadata.types.classes.MetaToK2Transient;
import com.k2.core.model.K2Service;
import com.k2.core.model.K2Type;
import com.k2.core.model.types.K2Class;
import com.k2.core.model.types.classes.K2Embeddable;
import com.k2.core.model.types.classes.K2Entity;
import com.k2.core.model.types.classes.K2Native;
import com.k2.core.model.types.classes.K2Transient;

public class MetaToK2Class extends MetaToDataConvertor {

	public MetaToK2Class(MetaData metaData) { super(metaData); }
	
	@SuppressWarnings("unchecked")
	public K2Class convert(Object obj) {
		
		MetaModelClass<?> meta = (MetaModelClass<?>)obj;
		K2Class conv = null;
		if (meta instanceof MetaModelEmbeddable)
			conv = metaData.getMetaToDataConvertor(K2Embeddable.class).convert((MetaModelEmbeddable<K2Embeddable>)meta);
		else if (meta instanceof MetaModelEntity)
			conv = metaData.getMetaToDataConvertor(K2Entity.class).convert((MetaModelEntity<K2Entity>)meta);
		else if (meta instanceof MetaModelNative)
			conv = metaData.getMetaToDataConvertor(K2Native.class).convert((MetaModelNative<K2Native>)meta);
		else if (meta instanceof MetaModelTransient)
			conv = metaData.getMetaToDataConvertor(K2Transient.class).convert((MetaModelTransient<K2Transient>)meta);
		else
			throw new MetaDataError("Unexpected super type of MetaModelClass.class - {}", meta.getClass().getName());
		
		conv.setType(K2Type.Type.CLASS);
//		conv.setFields(fields);
//		conv.setMethods(methods);
		
		if (meta.getMetaService() != null) {
			metaData.addLinkedMetaData(new LinkedMetaData<K2Class,K2Service>(conv, K2Service.class) {
				@Override public Object getMetaObject() { return meta.getMetaService(); }
				@Override public void setTarget(K2Service linkedData) { source.setService(linkedData); }
			});
		}
		
		return conv;
	}

	@Override
	public Serializable getKeyFor(Object obj) {
		MetaModelClass<?> meta = (MetaModelClass<?>)obj;
		return meta.className();
	}

}
