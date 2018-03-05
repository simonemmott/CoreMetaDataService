package com.k2.MetaModelService.metadata;

import com.k2.MetaModel.MetaModelEmbeddable;
import com.k2.MetaModelService.types.K2Class;
import com.k2.MetaModelService.types.K2Embeddable;
import com.k2.MetaModelService.types.K2Entity;

public class MetaDataEmbeddable {

	public static K2Embeddable toData(MetaData metadata, MetaModelEmbeddable<?> mme) {
		K2Embeddable k2Embeddable = new K2Embeddable(mme.className());
		k2Embeddable.setTableName(mme.tableName());
		k2Embeddable.setVersion(MetaDataVersion.toData(mme.version()));
		return k2Embeddable;
	}

}
