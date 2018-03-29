package com.k2.core.metadata.types;

import java.io.Serializable;

import com.k2.MetaModel.model.types.MetaModelSubType;
import com.k2.core.metadata.MetaData;
import com.k2.core.metadata.MetaToDataConvertor;
import com.k2.core.model.types.K2SubType;

public class MetaToK2SubType extends MetaToDataConvertor<K2SubType> {

	public MetaToK2SubType(MetaData metaData) { super(metaData); }
	
	@SuppressWarnings("unchecked")
	public K2SubType convert(Object obj) {
		MetaModelSubType<K2SubType, ?> meta = (MetaModelSubType<K2SubType, ?>)obj;
		K2SubType conv = new K2SubType(meta.className());
		return conv;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Serializable getKeyFor(Object obj) {
		MetaModelSubType<K2SubType, ?> meta = (MetaModelSubType<K2SubType, ?>)obj;
		return meta.className();
	}

}
