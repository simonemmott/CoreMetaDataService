package com.k2.MetaModelService.metadata;

import com.k2.MetaModel.MetaModelClass;
import com.k2.MetaModel.MetaModelEntity;
import com.k2.MetaModelService.types.K2Class;
import com.k2.MetaModelService.types.K2Entity;

public class MetaDataEntity {

	public static K2Entity toData(MetaData metadata, MetaModelEntity<?> mme) {
		K2Entity k2Entity = new K2Entity(mme.className());
		k2Entity.setEntityName(mme.entityName());
		k2Entity.setTableName(mme.tableName());
		k2Entity.setVersion(MetaDataVersion.toData(mme.version()));
		return k2Entity;
	}

}
