package com.k2.core.config;

import com.k2.MetaModel.annotations.MetaApplication;
import com.k2.MetaModel.annotations.MetaVersion;

@MetaApplication(
		alias="k2CoreMetaData",
		title="K2 Core MetaData",
		description="This is a dummy application to generate the core meta data from the core meta data service classes",
		version=@MetaVersion(major=0, minor=0, point=1, build=0),
		organisation="com.k2",
		website="www.k2.com",
		services= {CoreMetaDataServiceConfig.class}
		)
public class CoreMetaDataAppConfig {

}
