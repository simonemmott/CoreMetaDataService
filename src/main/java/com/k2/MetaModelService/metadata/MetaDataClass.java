package com.k2.MetaModelService.metadata;

import java.util.ArrayList;
import java.util.List;

import com.k2.MetaModel.model.MetaModelType;
import com.k2.MetaModel.model.types.MetaModelSubType;
import com.k2.MetaModel.model.types.classes.MetaModelEmbeddable;
import com.k2.MetaModel.model.types.classes.MetaModelEntity;
import com.k2.MetaModel.model.types.classes.MetaModelTransient;
import com.k2.MetaModelService.types.K2Class;
import com.k2.MetaModelService.types.K2SubType;
import com.k2.MetaModelService.types.K2Type;

public class MetaDataClass {

	public static K2Class toData(MetaData metadata, MetaModelType<?> mmc) {
		K2Class k2Class;
		if (mmc instanceof MetaModelEntity)
			k2Class = MetaDataEntity.toData(metadata, (MetaModelEntity<?>)mmc);
		else if (mmc instanceof MetaModelEmbeddable)
			k2Class = MetaDataEmbeddable.toData(metadata, (MetaModelEmbeddable<?>)mmc);
		else if (mmc instanceof MetaModelTransient)
			k2Class = MetaDataTransient.toData(metadata, (MetaModelTransient<?>)mmc);
		else
			throw new MetaDataError("Unexpected instance {} was neither MetaModelEntity, MetaModelEmbeddable nor MetaModelTransient", mmc.getClass().getName());

		k2Class.setDescription(mmc.description());
		k2Class.setTitle(mmc.title());
		k2Class.setAlias(mmc.alias());
		
		List<K2SubType> declaredSubTypes = new ArrayList<K2SubType>();
		
		for (MetaModelSubType<?,?> mmst : mmc.getDeclaredSubTypes()) 
			declaredSubTypes.add(MetaDataSubType.toData(metadata, mmst));
		
		k2Class.setDeclaredSubTypes(declaredSubTypes);

		return k2Class;
	}
	

}
