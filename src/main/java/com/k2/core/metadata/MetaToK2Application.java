package com.k2.core.metadata;

import java.io.Serializable;

import com.k2.MetaModel.model.MetaModel;
import com.k2.core.model.K2Application;
import com.k2.core.model.K2ApplicationId;
import com.k2.core.model.K2Service;
import com.k2.core.model.K2Version;

public class MetaToK2Application extends MetaToDataConvertor<K2Application> {

	MetaToK2Application(MetaData metaData) { super(metaData); }
	
	public K2Application convert(Object obj) {
		MetaModel meta = (MetaModel)obj;
		
		K2Application conv = metaData.getServiceManager().newEntity(K2Application.class);
		conv.setOrganisation(meta.organisation());
		conv.setAlias(meta.alias());

		conv.setTitle(meta.title());
		conv.setDescription(meta.description());
		conv.setVersion(new K2Version(meta.version()));
		conv.setWebsite(meta.website().toString());
		conv.setConfigClassName(meta.configurationClass().getName());
		
		return conv;
	}

	@Override
	public Serializable getKeyFor(Object obj) {
		MetaModel meta = (MetaModel)obj;
		return new K2ApplicationId(meta.organisation(), meta.alias());
	}
}
