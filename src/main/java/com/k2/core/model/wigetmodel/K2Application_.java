package com.k2.core.model.wigetmodel;

import java.util.Set;

import com.k2.Wiget.Wiget;
import com.k2.Wiget.WigetParameter;
import com.k2.core.model.K2ImplementedService;
import com.k2.core.model.K2Version;

@SuppressWarnings("rawtypes")
public class K2Application_<W extends Wiget> {
	public WigetParameter<W, String> organisation;
	public WigetParameter<W, String> alias;
	public WigetParameter<W, String> title;
	public WigetParameter<W, String> description;
	public WigetParameter<W, K2Version> version;
	public WigetParameter<W, String> website;
	public WigetParameter<W, String> configClassName;
	public WigetParameter<W, Set<K2ImplementedService>> implementedServices;

}
