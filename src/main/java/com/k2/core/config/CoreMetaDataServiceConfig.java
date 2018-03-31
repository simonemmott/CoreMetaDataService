package com.k2.core.config;

import com.k2.ConfigClass.ConfigClass;
import com.k2.ConfigClass.ConfigLocation;
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
@ConfigClass(
		filename="k2-core.conf",
		location=ConfigLocation.OS_FILE,
		dateFormat="dd-MM-yyyy"
		)
public class CoreMetaDataServiceConfig {

	private String metadataRepositoryPath;
	private String javaRepositoryPath;
	
	public String getMetadateRepositoryPath() { return metadataRepositoryPath; }
	public String getJavaRepositoryPath() { return javaRepositoryPath; }
}
