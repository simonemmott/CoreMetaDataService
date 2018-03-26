package com.k2.MetaModelService.metadata;

import com.k2.MetaModel.model.types.MetaModelSubType;
import com.k2.MetaModelService.types.K2SubType;

public class MetaDataSubType {

	public static K2SubType toData(MetaData metadata, MetaModelSubType<?, ?> mmst) {
		K2SubType subType = new K2SubType(mmst.className());
		metadata.registerType(subType);
		subType.setDescription(mmst.description());
		return subType;
	}

}
