package com.k2.core.model;

import java.io.Serializable;

public class K2FieldId implements Serializable {
	
	private static final long serialVersionUID = -3663389601311557743L;

	private String className;
	public String getClassName() { return className; }
	
	private String name;
	public String getName() { return name; }
	
	public K2FieldId() {}
	public K2FieldId(String packageName, String className, String name) {
		this.className = className;
		this.name = name;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((className == null) ? 0 : className.hashCode());
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
		K2FieldId other = (K2FieldId) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (className == null) {
			if (other.className != null)
				return false;
		} else if (!className.equals(other.className))
			return false;
		return true;
	}

	

}
