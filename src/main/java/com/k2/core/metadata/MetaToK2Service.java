package com.k2.core.metadata;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.k2.MetaModel.model.MetaModelService;
import com.k2.MetaModel.model.MetaModelType;
import com.k2.core.model.K2Service;
import com.k2.core.model.K2Type;
import com.k2.core.model.K2Version;
import com.k2.core.model.types.K2Interface;

public class MetaToK2Service extends MetaToDataConvertor<K2Service> {

	public MetaToK2Service(MetaData metaData) { super(metaData); }
	

	@Override
	public K2Service convert(Object obj) {
		MetaModelService meta = (MetaModelService)obj;
		K2Service conv = metaData.getServiceManager().newEntity(K2Service.class);
		conv.setAlias(meta.alias());

		conv.setDescription(meta.description());
		conv.setTitle(meta.title());
		conv.setVersion(new K2Version(meta.version()));
		conv.setConfigClassName(meta.serviceClass().getName());
		conv.setServiceInterfaceName(meta.serviceInterface().getName());
		conv.setServiceImplementationClassName(meta.serviceImplementation().getName());
		conv.setConfigFileName(meta.getConfigFileName());
		
		List<K2Type> allManagedTypes = new ArrayList<K2Type>();
		conv.setAllManagedTypes(allManagedTypes);
		for (MetaModelType<?> metaType : meta.getManagedTypes()) {
			metaData.addLinkedMetaData(new LinkedMetaData<List<K2Type>,K2Type>(allManagedTypes, K2Type.class) {
				@Override public Object getMetaObject() { return metaType; }
				@Override public void setTarget(K2Type linkedData) { source.add(linkedData); }
			});

		}
		
		
		return conv;
	}


	@Override
	public Serializable getKeyFor(Object obj) {
		MetaModelService meta = (MetaModelService)obj;
		return meta.alias();
	}


}
