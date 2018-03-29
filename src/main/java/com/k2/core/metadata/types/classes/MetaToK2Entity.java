package com.k2.core.metadata.types.classes;

import java.io.Serializable;

import com.k2.MetaModel.model.types.classes.MetaModelEntity;
import com.k2.core.metadata.MetaData;
import com.k2.core.metadata.MetaToDataConvertor;
import com.k2.core.model.K2Version;
import com.k2.core.model.types.K2Class;
import com.k2.core.model.types.classes.K2Entity;

public class MetaToK2Entity extends MetaToDataConvertor<K2Entity> {

	public MetaToK2Entity(MetaData metaData) { super(metaData); }
	
	@SuppressWarnings("unchecked")
	public K2Entity convert(Object obj) {
		MetaModelEntity<K2Entity> meta = (MetaModelEntity<K2Entity>)obj;
		K2Entity conv = new K2Entity(meta.className());
		conv.setClassType(K2Class.ClassType.ENTITY);
		conv.setTableName(meta.tableName());
		conv.setVersion(new K2Version(meta.version()));
		return conv;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Serializable getKeyFor(Object obj) {
		MetaModelEntity<K2Entity> meta = (MetaModelEntity<K2Entity>)obj;
		return meta.className();
	}

}
