package com.k2.core.service;

import com.k2.JavaFactory.JavaWriterException;

public interface CoreMetaDataService {

	public int testMethod(String str);

	public void writeAppConfig(String org, String alias) throws JavaWriterException;

	void writeService(String alias) throws JavaWriterException;

}
