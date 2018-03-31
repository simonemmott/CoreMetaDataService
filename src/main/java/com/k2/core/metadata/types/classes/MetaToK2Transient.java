package com.k2.core.metadata.types.classes;

import java.io.Serializable;

import com.k2.MetaModel.model.types.classes.MetaModelTransient;
import com.k2.core.metadata.MetaData;
import com.k2.core.metadata.MetaToDataConvertor;
import com.k2.core.model.K2Version;
import com.k2.core.model.types.K2Class;
import com.k2.core.model.types.classes.K2Native;
import com.k2.core.model.types.classes.K2Transient;

public class MetaToK2Transient extends MetaToDataConvertor<K2Transient> {

	public MetaToK2Transient(MetaData metaData) { super(metaData); }
	
	@SuppressWarnings("unchecked")
	public K2Transient convert(Object obj) {
		MetaModelTransient<K2Transient> meta = (MetaModelTransient<K2Transient>)obj;
		K2Transient conv = metaData.getServiceManager().newEntity(K2Transient.class);
		conv.setClassName(meta.className());
		conv.setClassType(K2Class.ClassType.TRANSIENT);
		conv.setVersion(new K2Version(meta.version()));
		return conv;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Serializable getKeyFor(Object obj) {
		MetaModelTransient<K2Transient> meta = (MetaModelTransient<K2Transient>)obj;
		return meta.className();
	}

}
