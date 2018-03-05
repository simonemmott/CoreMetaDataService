package com.k2.MetaModelService;

import com.k2.MetaModel.StaticMetaService;
import com.k2.MetaModel.annotations.MetaVersion;
import com.k2.MetaModel.annotations.StaticService;
import com.k2.Util.Version.Version;

@StaticService(alias="metaModel")
public class MetaModel_StaticMetaService implements StaticMetaService {
	
	public static final String alias = "metaModel";
	public static final String title = "The MetaModel Service";
	public static final Version version = Version.staticVersion(0,0,1,1);
	public static final String description = "The metamode service provides methods for creating, updating, and deleteing metamodel entities";
	public static final String[] typePackageNames = {"com.k2.MetaModelService.types"};
	
	@Override
	public String alias() { return alias;}

	@Override
	public String title() { return title; }

	@Override
	public Version version() { return version; }

	@Override
	public String description() { return description; }

	@Override
	public String[] typePackageNames() { return typePackageNames; }

}
