package com.k2.core.metadata;

import com.k2.Util.StringUtil;

public abstract class LinkedMetaData<S,T> {

	protected S source;
	private Class<T> linkToClass;
	protected LinkedMetaData(S source, Class<T> linkToClass) {
		this.source = source;
		this.linkToClass = linkToClass;
	}

	public abstract Object getMetaObject();
		
	public Class<T> getLinkToClass() { return linkToClass; }
	
	public abstract void setTarget(T linkedData);
	
	public String toString() {
		return StringUtil.replaceAll("Source: {}({}) LinkToClass: {}", "{}", source.getClass().getSimpleName(), source.toString(), linkToClass.getSimpleName());
	}


}
