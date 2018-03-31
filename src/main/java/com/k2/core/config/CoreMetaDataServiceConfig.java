package com.k2.core.config;

import com.k2.MetaModel.annotations.MetaService;
import com.k2.MetaModel.annotations.MetaVersion;
import com.k2.core.service.CoreMetaDataService;
import com.k2.core.service.CoreMetaDataServiceImpl;


@MetaService(
		alias="coreMetaDataService",
		title="The Core Metadata Service",
		description="The classes in this service are the core meta data classes",
		version=@MetaVersion(major=0, minor=0, point=1), 
		modelPackageNames = { "com.k2.core.model" },
		serviceInterface = CoreMetaDataService.class,
		serviceImplementation = CoreMetaDataServiceImpl.class
		)
public class CoreMetaDataServiceConfig {

}
