package com.k2.MetaModelService.metadata;

import com.k2.MetaModel.model.types.classes.MetaModelTransient;
import com.k2.MetaModelService.types.K2Class;
import com.k2.MetaModelService.types.K2Embeddable;
import com.k2.MetaModelService.types.K2Transient;

public class MetaDataTransient {

	public static K2Transient toData(MetaData metadata, MetaModelTransient<?> mmt) {
		K2Transient k2Transient = new K2Transient(mmt.className());
		k2Transient.setVersion(MetaDataVersion.toData(mmt.version()));
		return k2Transient;
	}

}
