package com.k2.core.service;

import java.lang.invoke.MethodHandles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.k2.MetaModel.annotations.MetaParameter;
import com.k2.MetaModel.annotations.MetaServiceMethod;
import com.k2.MetaModel.annotations.MetaServiceObject;

@MetaServiceObject
public class CoreMetaDataServiceImpl implements CoreMetaDataService {

	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	@MetaServiceMethod("testMethod")
	@Override
	public int testMethod(@MetaParameter("str") String str) {
		logger.info("Service method 'testMethod' invoked with str={}", str);
		return str.length();
	}
	
	@MetaServiceMethod("testMethod")
	@Override
	public void writeAppJavaSource() {
		logger.info("Writing application Java source code");
	}
}
