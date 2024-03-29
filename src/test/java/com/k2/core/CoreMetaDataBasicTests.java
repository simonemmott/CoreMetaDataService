package com.k2.core;

import static org.junit.Assert.*;

import java.io.File;
import java.lang.invoke.MethodHandles;
import java.util.Scanner;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.k2.MetaModel.model.MetaModel;
import com.k2.MetaModel.model.MetaModelService;
import com.k2.Service.dataAccess.K2Dao;
import com.k2.Service.service.ServiceManager;
import com.k2.Util.Version.Version;
import com.k2.Util.tuple.Pair;
import com.k2.core.config.CoreMetaDataAppConfig;
import com.k2.core.config.CoreMetaDataServiceConfig;
import com.k2.core.javaFactory.CoreJavaWriter;
import com.k2.core.metadata.MetaData;
import com.k2.core.model.K2Application;
import com.k2.core.model.K2ApplicationId;
import com.k2.core.model.K2ImplementedService;
import com.k2.core.model.K2Type;
import com.k2.core.model.types.K2Class;

public class CoreMetaDataBasicTests {
	
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
		
	private static MetaData metaData = MetaData.fromMetaModel(MetaModel.reflect(CoreMetaDataAppConfig.class));

	@Test
	public void generateMetaDataTest() 
    {
		K2Dao<K2Type,String> dao = metaData.getDao(K2Type.class, String.class);
		for (K2Type type : dao.list()) {
			if (type.getType() == K2Type.Type.CLASS) {
				K2Class k2Class = (K2Class)type;
				logger.info("Id: {} Type: {} Alias: {} Title: {} Description: {} - {}", k2Class.getId(), k2Class.getClassType(), k2Class.getAlias(), k2Class.getTitle(), k2Class.getDescription(), k2Class.getClassName());
			}
			else
				logger.info("Id: {} Type: {} Alias: {} Title: {} Description: {} - {}", type.getId(), type.getType(), type.getAlias(), type.getTitle(), type.getDescription(), type.getClassName());
		}
    }
	
	@Test
	public void k2ApplicationTest() {
		
		ServiceManager serviceManager = metaData.getServiceManager();
				
		K2Application app = serviceManager.find(K2Application.class, "com.k2", "k2CoreMetaData");
						
		assertNotNull(app);
		assertEquals("com.k2", app.getOrganisation());
		assertEquals("k2CoreMetaData", app.getAlias());
		assertEquals("K2 Core MetaData", app.getTitle());
		assertEquals("This is a dummy application to generate the core meta data from the core meta data service classes", app.getDescription());
		assertNotNull(app.getVersion());
		assertEquals(Version.create(0, 0, 1), Version.create(
				app.getVersion().major(), 
				app.getVersion().minor(), 
				app.getVersion().point()));
		assertEquals("http://www.k2.com", app.getWebsite());
		
		assertEquals(1, app.getImplementedServices().size());
		
		K2ImplementedService implementedService = (K2ImplementedService) app.getImplementedServices().toArray()[0];
		
		assertEquals("com.k2", implementedService.getOrganisation());
		assertEquals("k2CoreMetaData", implementedService.getAlias());
		assertEquals("coreMetaDataService", implementedService.getServiceAlias());
		
		
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Test
	public void serviceMethodInvokationTest() throws Throwable {
		assertEquals(23, metaData.getServiceManager().invokeServiceMethod("testMethod", new Pair("str", "This is a value for str")));
	}
	
	@Test
	public void configurationTest() {
		metaData.getMetaModel().configure("/Users/simon/Personal/K2_Workshop/example/conf");
		
		MetaModelService metaService = metaData.getMetaServicel();
		CoreMetaDataServiceConfig config = metaService.getConfiguration(CoreMetaDataServiceConfig.class);
		
		assertNotNull(config);
		
		assertEquals("/Users/simon/Personal/K2_Workshop/example/json", config.getMetadateRepositoryPath());
		assertEquals("/Users/simon/Personal/K2_Workshop/example/src", config.getJavaRepositoryPath());
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Test
	public void appWriterTest() throws Throwable {
		metaData.getMetaModel().configure("/Users/simon/Personal/K2_Workshop/example/conf");

		File coreMetaDataAppConfig_java = new File("/Users/simon/Personal/K2_Workshop/example/src/com/k2/core/config/CoreMetaDataAppConfig.java");
		if (coreMetaDataAppConfig_java.exists())
			coreMetaDataAppConfig_java.delete();
		
		assertFalse(coreMetaDataAppConfig_java.exists());

		metaData.getServiceManager().invokeServiceMethod("writeAppConfig", new Pair("org", "com.k2"), new Pair("alias", "k2CoreMetaData"));	
		
		assertTrue(coreMetaDataAppConfig_java.exists());
		
		String content = new Scanner(coreMetaDataAppConfig_java).useDelimiter("\\Z").next();
		
//		System.out.println(content);
		
		String expected = 
				"package com.k2.core.config;\n" + 
				"\n" + 
				"\n" + 
				"import com.k2.MetaModel.annotations.MetaApplication;\n" + 
				"import com.k2.MetaModel.annotations.MetaVersion;\n" + 
				"\n" + 
				"@MetaApplication(alias = \"k2CoreMetaData\", \n" + 
				"	title = \"K2 Core MetaData\", \n" + 
				"	description = \"This is a dummy application to generate the core meta data from the core meta data service classes\", \n" + 
				"	version = @MetaVersion(major = 0, minor = 0, point = 1, build = 0), \n" + 
				"	organisation = \"com.k2\", \n" + 
				"	website = \"http://www.k2.com\", \n" + 
				"	services = {\n" + 
				"		CoreMetaDataServiceConfig.class\n" + 
				"	})\n" + 
				"class CoreMetaDataAppConfig {\n" + 
				"\n" + 
				"}";
		
		assertEquals(expected, content);
		
		
		
	}


	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Test
	public void serviceWriterTest() throws Throwable {
		metaData.getMetaModel().configure("/Users/simon/Personal/K2_Workshop/example/conf");

		File coreMetaDataServiceConfig_java = new File("/Users/simon/Personal/K2_Workshop/example/src/com/k2/core/config/CoreMetaDataServiceConfig.java");
		if (coreMetaDataServiceConfig_java.exists())
			coreMetaDataServiceConfig_java.delete();
		
		assertFalse(coreMetaDataServiceConfig_java.exists());

		metaData.getServiceManager().invokeServiceMethod("writeService", new Pair("alias", "coreMetaDataService"));	
		
		assertTrue(coreMetaDataServiceConfig_java.exists());
		
		String content = new Scanner(coreMetaDataServiceConfig_java).useDelimiter("\\Z").next();
		
//		System.out.println(content);
		
		String expected = 
				"package com.k2.core.config;\n" + 
				"\n" + 
				"\n" + 
				"import com.k2.ConfigClass.ConfigClass;\n" + 
				"import com.k2.ConfigClass.ConfigLocation;\n" + 
				"import com.k2.MetaModel.annotations.MetaService;\n" + 
				"import com.k2.MetaModel.annotations.MetaVersion;\n" + 
				"import com.k2.core.service.CoreMetaDataService;\n" + 
				"import com.k2.core.service.CoreMetaDataServiceImpl;\n" + 
				"\n" + 
				"@ConfigClass(filename = \"k2-core.conf\", \n" + 
				"	location = ConfigLocation.OS_FILE, \n" + 
				"	dateFormat = \"dd-MM-yyyy\")\n" + 
				"@MetaService(alias = \"coreMetaDataService\", \n" + 
				"	title = \"The Core Metadata Service\", \n" + 
				"	description = \"The classes in this service are the core meta data classes\", \n" + 
				"	version = @MetaVersion(major = 0, minor = 0, point = 1, build = 0), \n" + 
				"	modelPackageNames = {\n" + 
				"		\"com.k2.core.model\", \n" + 
				"		\"com.k2.core.model.types\", \n" + 
				"		\"com.k2.core.model.types.classes\"\n" + 
				"	}, \n" + 
				"	serviceInterface = CoreMetaDataService.class, \n" + 
				"	serviceImplementation = CoreMetaDataServiceImpl.class)\n" + 
				"class CoreMetaDataServiceConfig {\n" + 
				"\n" + 
				"}";
		
		assertEquals(expected, content);
		
		
		
	}
}
