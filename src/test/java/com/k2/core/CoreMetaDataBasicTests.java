package com.k2.core;

import static org.junit.Assert.*;

import java.lang.invoke.MethodHandles;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.k2.K2Service.dataAccess.K2Dao;
import com.k2.MetaModel.model.MetaModel;
import com.k2.core.config.CoreMetaDataAppConfig;
import com.k2.core.metadata.MetaData;
import com.k2.core.model.K2Type;
import com.k2.core.model.types.K2Class;

public class CoreMetaDataBasicTests {
	
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
		

	@Test
	public void generateMetaDataTest() 
    {
		
		MetaData metaData = MetaData.fromMetaModel(MetaModel.reflect(CoreMetaDataAppConfig.class));
		
		K2Dao<K2Type,String> dao = metaData.getDao(K2Type.class, String.class);
		
		for (K2Type type : dao.list()) {
			if (type.getType() == K2Type.Type.CLASS) {
				K2Class k2Class = (K2Class)type;
				logger.info("Type: {} Alias: {} Title: {} Description: {} - {}", k2Class.getClassType(), k2Class.getAlias(), k2Class.getTitle(), k2Class.getDescription(), k2Class.getClassName());
			}
			else
				logger.info("Type: {} Alias: {} Title: {} Description: {} - {}", type.getType(), type.getAlias(), type.getTitle(), type.getDescription(), type.getClassName());

		}
		

    }
	
	
}
