package com.k2.core.metadata.types.classes;

import java.io.Serializable;

import com.k2.MetaModel.model.types.classes.MetaModelNative;
import com.k2.core.metadata.MetaData;
import com.k2.core.metadata.MetaToDataConvertor;
import com.k2.core.model.types.K2Class;
import com.k2.core.model.types.classes.K2Entity;
import com.k2.core.model.types.classes.K2Native;

public class MetaToK2Native extends MetaToDataConvertor<K2Native> {

	public MetaToK2Native(MetaData metaData) { super(metaData); }
	
	@SuppressWarnings("unchecked")
	public K2Native convert(Object obj) {
		MetaModelNative<K2Native> meta = (MetaModelNative<K2Native>)obj;
		K2Native conv = metaData.getServiceManager().newEntity(K2Native.class);
		conv.setClassName(meta.className());
		conv.setClassType(K2Class.ClassType.NATIVE);
		return conv;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Serializable getKeyFor(Object obj) {
		MetaModelNative<K2Native> meta = (MetaModelNative<K2Native>)obj;
		return meta.className();
	}

}
