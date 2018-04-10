package com.k2.core.service;

import java.lang.invoke.MethodHandles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.k2.JavaFactory.JavaWriterException;
import com.k2.MetaModel.annotations.MetaParameter;
import com.k2.MetaModel.annotations.MetaServiceMethod;
import com.k2.MetaModel.annotations.MetaServiceObject;
import com.k2.Service.service.ServiceManager;
import com.k2.core.config.CoreMetaDataServiceConfig;
import com.k2.core.javaFactory.CoreJavaWriter;
import com.k2.core.javaFactory.CoreJavaWriter;
import com.k2.core.metadata.MetaDataError;
import com.k2.core.model.K2Application;
import com.k2.core.model.K2Service;

@MetaServiceObject
public class CoreMetaDataServiceImpl implements CoreMetaDataService {

	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	
	ServiceManager serviceManager;
	CoreJavaWriter coreJavaWriter;
	
	public void setServiceManager(ServiceManager serviceManager) {
		this.serviceManager = serviceManager;
	}
	
	private void setJavaWriter() {
		String javaRepo = serviceManager.getMetaService().getConfiguration(CoreMetaDataServiceConfig.class).getJavaRepositoryPath();
		try {
			logger.info("Starting Java writer on {}", javaRepo);
			this.coreJavaWriter = new CoreJavaWriter(javaRepo);
		} catch (JavaWriterException e) {
			throw new MetaDataError("Unable to prepare the Java writer on output location {}", javaRepo);
		}		
	}

	@MetaServiceMethod("testMethod")
	@Override
	public int testMethod(@MetaParameter("str") String str) {
		logger.trace("Invoking CoreMetaDataService.testMethod({})", str);
		return str.length();
	}
	
	@MetaServiceMethod("writeAppConfig")
	@Override
	public void writeAppConfig(@MetaParameter("org")String org, @MetaParameter("alias")String alias) throws JavaWriterException {
		logger.trace("Invoking CoreMetaDataService.writeAppConfig({}, {})", org, alias);
		K2Application app = serviceManager.find(K2Application.class, org, alias);
		if (coreJavaWriter == null)
			setJavaWriter();
		coreJavaWriter.writeAppConfig(app);
	}
	
	@MetaServiceMethod("writeService")
	@Override
	public void writeService(@MetaParameter("alias")String alias) throws JavaWriterException {
		logger.trace("Invoking CoreMetaDataService.writeService({})", alias);
		K2Service service = serviceManager.find(K2Service.class, alias);
		if (coreJavaWriter == null)
			setJavaWriter();
		coreJavaWriter.writeService(service);
	}
	
}
