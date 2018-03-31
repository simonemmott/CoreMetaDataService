package com.k2.core.metadata;

import java.io.Serializable;
import java.util.List;

import com.k2.Service.dataAccess.K2Dao;
import com.k2.Util.KeyUtil;

public class MetaDataDao<E, K extends Serializable> implements K2Dao<E, K> {
	
	private ClassCache<E> cache;
	private Class<E> daoType;
	private Class<K> keyType;
	MetaDataDao(MetaData metaData, Class<E> daoType, Class<K> keyType) {
		this.daoType = daoType;
		this.keyType = keyType;
		this.cache = metaData.getClassCache(daoType);
	}

	@Override public Class<E> daoType() { return daoType; }

	@Override public Class<K> keyType() { return keyType; }

	@SuppressWarnings("unchecked")
	@Override public K key(Serializable... values) { return (K) KeyUtil.constructKey((Class<Serializable>) keyType, (Object[])values); }

	@Override public E find(Serializable key) { return cache.find(key); }

	@Override public E insert(E entity) { return cache.persist(entity); }

	@Override public E update(E entity) { return cache.merge(entity); }

	@Override public void delete(E entity) { cache.remove(entity); }

	@Override public List<E> list() { return cache.list(); }

}
