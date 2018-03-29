package com.k2.core.metadata;

import java.io.Serializable;

public abstract class MetaToDataConvertor<T> {

	protected MetaData metaData;
	
	protected MetaToDataConvertor(MetaData metaData) {
		this.metaData = metaData;
	}
	
	public abstract T convert(Object obj);

	public abstract Serializable getKeyFor(Object obj);
	
}
