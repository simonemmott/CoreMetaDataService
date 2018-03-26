package com.k2.MetaModelService;

import static org.junit.Assert.*;

import java.lang.invoke.MethodHandles;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.k2.MetaModel.model.MetaModel;
import com.k2.MetaModel.model.MetaModelService;
import com.k2.MetaModelService.metadata.MetaData;
import com.k2.MetaModelService.types.K2Type;
import com.k2.Util.Version.Version;

public class MetaModelBasicTests {
	
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
		
	private MetaModel metaModel;

	@Test
	public void readApplicationConfigTest() 
    {
		
		assertEquals("dummyApp", metaModel.alias());
		assertEquals("A dummy application for testing the MetaModel service", metaModel.description());
		assertEquals("A Dummy Application", metaModel.title());
		assertTrue(Version.create(0, 0, 0).equals(metaModel.version()));
		assertEquals("http://www.dummy.org", metaModel.website().toString());		
		
		assertEquals(1, metaModel.implementedServices().size());

		MetaModelService mms = metaModel.metaModelService("metaModel");
		
		assertNotNull(mms);
		
		assertEquals("metaModel", mms.alias());
		assertEquals("The MetaModel Service", mms.title());
		assertTrue(Version.create(0,0,1).equals(mms.version()));
		assertEquals("The metamode service provides methods for creating, updating, and deleteing metamodel entities", mms.description());
		assertEquals("metaModel", mms.alias());
		assertEquals("metaModel", mms.alias());
		
		assertEquals(13, mms.getManagedClasses().size());

    }
	
	@Test
	public void generateMetaDataTest() 
    {
		
		MetaData metaData = MetaData.fromMetaModel(metaModel);
		
		assertNotNull(metaData);
		
		assertEquals(1, metaData.getImplementedServices().size());
		
		for (K2Type type : metaData.getAllTypes())
			logger.info("Type: {} Class: {}", type.getType(), type.getClassName());
		
		assertEquals(33, metaData.getAllTypes().size());
		assertEquals(8, metaData.getPrimitives().size());
		assertEquals(2, metaData.getSubTypes().size());
		assertEquals(0, metaData.getInterfaces().size());
		assertEquals(23, metaData.getClasses().size());
		assertEquals(10, metaData.getNatives().size());
		assertEquals(12, metaData.getEntities().size());
		assertEquals(1, metaData.getEmbeddables().size());
		assertEquals(0, metaData.getTransients().size());
		

    }
	
	
}
