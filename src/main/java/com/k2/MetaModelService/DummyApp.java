package com.k2.MetaModelService;

import java.net.URL;

import com.k2.MetaModel.StaticMetaApplication;
import com.k2.MetaModel.StaticMetaService;
import com.k2.MetaModel.annotations.StaticApplication;
import com.k2.Util.Version.Version;

@StaticApplication(alias="dummyApp")
public class DummyApp implements StaticMetaApplication {
	
	public static final String alias = "dummyApp";
	public static final String title = "A Dummy Application";
	public static final Version version = Version.staticVersion(0, 0, 0, 0);
	public static final String description = "A dummy application for testing the MetaModel service";
	public static final String organisation = "Dummy org";
	public static final String website = "http://www.dummy.org";
	public static final Class<?>[] implementedServiceClasses = { MetaModel_StaticMetaService.class };

	@Override
	public String alias() { return alias; }

	@Override
	public String title() { return title; }

	@Override
	public Version version() { return version; }

	@Override
	public String description() { return description; }

	@SuppressWarnings("unchecked")
	@Override
	public Class<? extends StaticMetaService>[] implementedServiceClasses() { return (Class<? extends StaticMetaService>[]) implementedServiceClasses; }

	@Override
	public String organisation() { return organisation; }

	@Override
	public URL website() { return StaticMetaApplication.toURL(website); }

}
