package com.k2.core;

import static org.junit.Assert.*;

import java.lang.invoke.MethodHandles;

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
				
		K2Application app = serviceManager.find(K2Application.class, "k2.com", "k2CoreMetaData");
						
		assertNotNull(app);
		assertEquals("k2.com", app.getOrganisation());
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
		
		assertEquals("k2.com", implementedService.getOrganisation());
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
}
