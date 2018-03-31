package com.k2.core.metadata.types.classes;

import java.io.Serializable;

import com.k2.MetaModel.model.types.classes.MetaModelEmbeddable;
import com.k2.core.metadata.MetaData;
import com.k2.core.metadata.MetaToDataConvertor;
import com.k2.core.model.K2Version;
import com.k2.core.model.types.K2Class;
import com.k2.core.model.types.K2SubType;
import com.k2.core.model.types.classes.K2Embeddable;

public class MetaToK2Embeddable extends MetaToDataConvertor<K2Embeddable> {

	public MetaToK2Embeddable(MetaData metaData) { super(metaData); }
	
	@SuppressWarnings("unchecked")
	public K2Embeddable convert(Object obj) {
		MetaModelEmbeddable<K2Embeddable> meta = (MetaModelEmbeddable<K2Embeddable>)obj;
		K2Embeddable conv = metaData.getServiceManager().newEntity(K2Embeddable.class);
		conv.setClassName(meta.className());
		conv.setClassType(K2Class.ClassType.EMBEDDABLE);
		conv.setTableName(meta.tableName());
		conv.setVersion(new K2Version(meta.version()));
		return conv;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Serializable getKeyFor(Object obj) {
		MetaModelEmbeddable<K2Embeddable> meta = (MetaModelEmbeddable<K2Embeddable>)obj;
		return meta.className();
	}

}
