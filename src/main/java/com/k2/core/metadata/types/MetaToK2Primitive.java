package com.k2.core.metadata.types;

import java.io.Serializable;

import com.k2.MetaModel.model.types.MetaModelPrimitive;
import com.k2.core.metadata.LinkedMetaData;
import com.k2.core.metadata.MetaData;
import com.k2.core.metadata.MetaToDataConvertor;
import com.k2.core.model.K2Type;
import com.k2.core.model.types.K2Interface;
import com.k2.core.model.types.K2Primitive;
import com.k2.core.model.types.classes.K2Native;

public class MetaToK2Primitive extends MetaToDataConvertor<K2Primitive> {

	public MetaToK2Primitive(MetaData metaData) { super(metaData); }
	

	@SuppressWarnings("unchecked")
	@Override
	public K2Primitive convert(Object obj) {
		MetaModelPrimitive<K2Primitive> meta = (MetaModelPrimitive<K2Primitive>)obj;
		K2Primitive conv =  metaData.getServiceManager().newEntity(K2Primitive.class);
		conv.setClassName(meta.className());
		
		if (meta.metaNativeClass() != null) {
			metaData.addLinkedMetaData(new LinkedMetaData<K2Primitive,K2Native>(conv, K2Native.class) {
				@Override public Object getMetaObject() { return meta.metaNativeClass(); }
				@Override public void setTarget(K2Native linkedData) { source.setImplementingNativeClass(linkedData); }
			});
		}
		
		conv.setType(K2Type.Type.PRIMITIVE);
		return conv;
	}


	@SuppressWarnings("unchecked")
	@Override
	public Serializable getKeyFor(Object obj) {
		MetaModelPrimitive<K2Primitive> meta = (MetaModelPrimitive<K2Primitive>)obj;
		return meta.className();
	}

}
