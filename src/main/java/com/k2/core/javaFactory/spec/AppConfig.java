package com.k2.core.javaFactory.spec;

import com.k2.Wiget.annotation.WigetSpecification;
import com.k2.JavaFactory.JavaWiget;
import com.k2.core.model.K2Application;
import com.k2.core.model.wigetmodel.K2Application_;

@WigetSpecification
public interface AppConfig extends JavaWiget<K2Application> {
	
	public static final K2Application_<AppConfig> model = new K2Application_<AppConfig>();
	@Override public default Object staticModel() { return model; }
	@Override public default Class<?> modelType() { return K2Application_.class; }
	
}
