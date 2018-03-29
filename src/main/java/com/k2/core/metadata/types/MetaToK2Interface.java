package com.k2.core.metadata.types;

import java.io.Serializable;

import com.k2.MetaModel.model.types.MetaModelInterface;
import com.k2.core.metadata.LinkedMetaData;
import com.k2.core.metadata.MetaData;
import com.k2.core.metadata.MetaToDataConvertor;
import com.k2.core.model.K2Service;
import com.k2.core.model.K2Type;
import com.k2.core.model.K2Version;
import com.k2.core.model.types.K2Interface;

public class MetaToK2Interface extends MetaToDataConvertor<K2Interface> {

	public MetaToK2Interface(MetaData metaData) { super(metaData); }
	
	@SuppressWarnings("unchecked")
	public K2Interface convert(Object obj) {
		MetaModelInterface<K2Interface> meta = (MetaModelInterface<K2Interface>)obj;
		K2Interface conv = new K2Interface(meta.className());
		
		conv.setType(K2Type.Type.INTERFACE);
//		conv.setMethods(methods);
		conv.setVersion(new K2Version(meta.version()));
		
		metaData.addLinkedMetaData(new LinkedMetaData<K2Interface,K2Service>(conv, K2Service.class) {
			@Override public Object getMetaObject() { return meta.getMetaService(); }
			@Override public void setTarget(K2Service linkedData) { source.setService(linkedData); }
		});
		
		return conv;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Serializable getKeyFor(Object obj) {
		MetaModelInterface<K2Interface> meta = (MetaModelInterface<K2Interface>)obj;
		return meta.className();
	}

}
