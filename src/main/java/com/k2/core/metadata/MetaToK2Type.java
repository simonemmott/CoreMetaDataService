package com.k2.core.metadata;

import java.io.Serializable;

import com.k2.MetaModel.model.MetaModelType;
import com.k2.MetaModel.model.types.MetaModelClass;
import com.k2.MetaModel.model.types.MetaModelInterface;
import com.k2.MetaModel.model.types.MetaModelPrimitive;
import com.k2.MetaModel.model.types.MetaModelSubType;
import com.k2.core.metadata.types.MetaToK2Class;
import com.k2.core.metadata.types.MetaToK2Interface;
import com.k2.core.metadata.types.MetaToK2Primitive;
import com.k2.core.metadata.types.MetaToK2SubType;
import com.k2.core.model.K2Type;
import com.k2.core.model.types.K2Class;
import com.k2.core.model.types.K2Interface;
import com.k2.core.model.types.K2Primitive;
import com.k2.core.model.types.K2SubType;

public class MetaToK2Type extends MetaToDataConvertor<K2Type> {

	MetaToK2Type(MetaData metaData) { super(metaData); }
	
	@SuppressWarnings("unchecked")
	public K2Type convert(Object obj) {
		MetaModelType<?> meta = (MetaModelType<?>)obj;
		
		K2Type conv = null;
		if (meta instanceof MetaModelClass)
			conv = metaData.getMetaToDataConvertor(K2Class.class).convert((MetaModelClass<K2Class>)meta);
		else if (meta instanceof MetaModelInterface)
			conv = metaData.getMetaToDataConvertor(K2Interface.class).convert((MetaModelInterface<K2Interface>)meta);
		else if (meta instanceof MetaModelPrimitive)
			conv = metaData.getMetaToDataConvertor(K2Primitive.class).convert((MetaModelPrimitive<K2Primitive>)meta);
		else if (meta instanceof MetaModelSubType)
			conv = metaData.getMetaToDataConvertor(K2SubType.class).convert((MetaModelSubType<K2SubType, ?>)meta);
		else
			throw new MetaDataError("Unexpected super type of MetaModelType.class - {}", meta.getClass().getName());
		
		
		conv.setAlias(meta.alias());
		conv.setTitle(meta.title());
		conv.setDescription(meta.description());
		
		return conv;
	}

	@Override
	public Serializable getKeyFor(Object obj) {
		MetaModelType<?> meta = (MetaModelType<?>)obj;
		return meta.className();
	}
}
