package com.k2.MetaModelService.metadata;

import java.util.ArrayList;
import java.util.List;

import com.k2.MetaModel.model.MetaModelService;
import com.k2.MetaModel.model.MetaModelType;
import com.k2.MetaModelService.types.K2Class;
import com.k2.MetaModelService.types.K2Embeddable;
import com.k2.MetaModelService.types.K2Entity;
import com.k2.MetaModelService.types.K2Service;
import com.k2.MetaModelService.types.K2Transient;
import com.k2.MetaModelService.types.K2Type;
import com.k2.MetaModelService.types.K2Version;

public class MetaDataService {

	public static K2Service toData(MetaData metadata, MetaModelService mms) {
		K2Service service = new K2Service(mms.alias());
		service.setTitle(mms.title());
		service.setDescription(mms.description());
		service.setVersion(MetaDataVersion.toData(mms.version()));
		
		List<K2Type> allManagedTypes = new ArrayList<K2Type>();
		List<K2Entity> managedEntities = new ArrayList<K2Entity>();
		List<K2Embeddable> managedEmbeddables = new ArrayList<K2Embeddable>();
		List<K2Transient> managedTransients = new ArrayList<K2Transient>();

		for (MetaModelType<?> mmc : mms.getManagedTypes()) {
			K2Class k2Class = (K2Class) metadata.registerType(MetaDataClass.toData(metadata, mmc));
			k2Class.setService(service);
			allManagedTypes.add(k2Class);
			if (k2Class instanceof K2Entity)
				managedEntities.add((K2Entity)k2Class);
			else if (k2Class instanceof K2Embeddable)
				managedEmbeddables.add((K2Embeddable)k2Class);
			else if (k2Class instanceof K2Transient)
				managedTransients.add((K2Transient)k2Class);
		}
		
		service.setAllManagedTypes(allManagedTypes);
		service.setManagedEntities(managedEntities);
		service.setManagedEmbeddables(managedEmbeddables);
		service.setManagedTransients(managedTransients);
		return service;
	}

}
