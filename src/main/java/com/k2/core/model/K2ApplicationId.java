package com.k2.core.model;

import java.io.Serializable;

public class K2ApplicationId implements Serializable {

	private static final long serialVersionUID = 7095174912618209049L;

	private String organisation;
	public String getOrganisation() { return organisation; }
	
	private String alias;
	public String getAlias() { return alias; }
	
	public K2ApplicationId() {}
	public K2ApplicationId(String organisation, String alias) {
		this.organisation = organisation;
		this.alias = alias;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((organisation == null) ? 0 : organisation.hashCode());
		result = prime * result + ((alias == null) ? 0 : alias.hashCode());
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
		K2ApplicationId other = (K2ApplicationId) obj;
		if (organisation == null) {
			if (other.organisation != null)
				return false;
		} else if (!organisation.equals(other.organisation))
			return false;
		if (alias == null) {
			if (other.alias != null)
				return false;
		} else if (!alias.equals(other.alias))
			return false;
		return true;
	}

	

}
