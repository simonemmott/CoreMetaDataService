package com.k2.core.metadata;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.Entity;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import com.k2.Util.KeyUtil;
import com.k2.Util.StringUtil;
import com.k2.Util.Identity.IdentityUtil;

public class ClassCache<T> {
	
	private Map<Serializable, T> cache = new HashMap<Serializable, T>();
	
	private MetaData metaData;
	private Class<T> cachedClass;
	public Class<T> getCachedClass() { return cachedClass; }
	private ClassCache<? super T> superClassCache;
	private Map<Class<? extends T>, ClassCache<? extends T>> subClassCaches;
	
	ClassCache(MetaData metaData, Class<T> cachedClass) {
		this.metaData = metaData;
		this.cachedClass = cachedClass;
		if (cachedClass.getSuperclass() != Object.class && cachedClass.getSuperclass().isAnnotationPresent(Entity.class)) {
			superClassCache = metaData.getClassCache(cachedClass.getSuperclass());
			superClassCache.addSubClassCache(this);
		}
	}
	
	@SuppressWarnings("unchecked")
	private <S extends T> ClassCache<S> addSubClassCache(ClassCache<S> subClassCache) {
		if (subClassCaches == null)
			subClassCaches = new HashMap<Class<? extends T>, ClassCache<? extends T>>();
		if (subClassCaches.containsKey(subClassCache.getCachedClass())) {
			ClassCache<S> currentSubCache = (ClassCache<S>) subClassCaches.get(subClassCache.getCachedClass());
			for (Entry<Serializable, S> entry : subClassCache.cache.entrySet())
				if ( ! currentSubCache.containsKey(entry.getKey()))
					currentSubCache.put(entry.getKey(), entry.getValue());
			return currentSubCache;
		} else {
			subClassCaches.put(subClassCache.getCachedClass(), subClassCache);
			return subClassCache;
		}
	}
	
	public T get(Serializable key) { return cache.get(key); }

	public List<T> list() {
		if (Comparable.class.isAssignableFrom(cachedClass)) {
			TreeSet<T> set = new TreeSet<T>();
			for(T t : cache.values())
				set.add(t);
			List<T> list = new ArrayList<T>(cache.size());
			for (T t : set)
				list.add(t);
			return list;
		}
		List<T> list = new ArrayList<T>(cache.size());
		for(T t : cache.values())
			list.add(t);
		return list;
	}

	public boolean containsKey(Serializable key) { return cache.containsKey(key); }

	private void put(Serializable key, T instance) { 
		cache.put(key, instance);
		if (superClassCache != null) {
			superClassCache.put(key, instance);
		}
		
	}

	private void remove(Serializable key, T instance) { 
		cache.remove(key);
		if (superClassCache != null) {
			superClassCache.remove(key, instance);
		}
	}
	
	T find(Serializable key) {
		return (T) get(key);
	}
	
	@SuppressWarnings("unchecked")
	T persist(T instance) {
		Class<T> cls = (Class<T>) instance.getClass();
		if (cls != cachedClass)
			return metaData.getClassCache(cls).persist(instance);
		
		Serializable key = IdentityUtil.getId(instance);
		if (key == null)
			throw new MetaDataError("Unable to persist instance of {} with a null Id", cls.getName());

		if (containsKey(key))
			throw new EntityExistsException(StringUtil.replaceAll("An instance of the Class{} already exists with the key {}", "{}", cls.getName(), KeyUtil.serialize(key)));
		put(key, instance);
		return instance;
	}
	
	@SuppressWarnings("unchecked")
	T merge(T instance) {
		Class<T> cls = (Class<T>) instance.getClass();
		if (cls != cachedClass)
			return metaData.getClassCache(cls).merge(instance);
		
		Serializable key = IdentityUtil.getId(instance);
		if (key == null)
			throw new MetaDataError("Unable to merge instance of {} with a null Id", cls.getName());
		if (containsKey(key)) {
			put(key, instance);
			return instance;
		}
		throw new EntityNotFoundException(StringUtil.replaceAll("No instance of the Class {} exists with the key {} when merging", "{}", cls.getName(), KeyUtil.serialize(key)));
	}
	
	@SuppressWarnings("unchecked")
	T remove(T instance) {
		Class<T> cls = (Class<T>) instance.getClass();
		if (cls != cachedClass)
			return metaData.getClassCache(cls).remove(instance);
		
		Serializable key = IdentityUtil.getId(instance);
		if (key == null)
			throw new MetaDataError("Unable to remove instance of {} with a null Id", cls.getName());
		if (containsKey(key)) {
			remove(key, instance);
			return instance;
		}
		throw new EntityNotFoundException(StringUtil.replaceAll("No instance of the Class{} exists with the key {} when deleting", "{}", cls.getName(), KeyUtil.serialize(key)));
	}


}
