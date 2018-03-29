package com.k2.core.model;

import java.io.Serializable;

public class K2MethodId implements Serializable {
	
	private static final long serialVersionUID = -3663389601311557743L;

	private String className;
	public String getClassName() { return className; }
	
	private Long methodId;
	public Long getMethodId() { return methodId; }
	
	public K2MethodId() {}
	public K2MethodId(String packageName, String className, Long methodId) {
		this.className = className;
		this.methodId = methodId;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((className == null) ? 0 : className.hashCode());
		result = prime * result + ((methodId == null) ? 0 : methodId.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		K2MethodId other = (K2MethodId) obj;
		if (className == null) {
			if (other.className != null)
				return false;
		} else if (!className.equals(other.className))
			return false;
		if (methodId == null) {
			if (other.methodId != null)
				return false;
		} else if (!methodId.equals(other.methodId))
			return false;
		return true;
	}

	

}
