package com.k2.core.config;

import com.k2.MetaModel.annotations.MetaService;
import com.k2.MetaModel.annotations.MetaVersion;


@MetaService(
		alias="coreMetaDataService",
		title="The Core Metadata Service",
		description="The classes in this service are the core meta data classes",
		version=@MetaVersion(major=0, minor=0, point=1), 
		modelPackageNames = { "com.k2.core.model" }
		)
public class CoreMetaDataServiceConfig {

}
