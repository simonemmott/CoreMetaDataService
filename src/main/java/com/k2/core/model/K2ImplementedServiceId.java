package com.k2.core.model;

import java.io.Serializable;

public class K2ImplementedServiceId implements Serializable {

	private static final long serialVersionUID = -7289064258462711050L;

	private String organisation;
	public String getOrganisation() { return organisation; }
	
	private String alias;
	public String getAlias() { return alias; }
	
	private String serviceAlias;
	public String getServiceAlias() { return serviceAlias; }
	
	public K2ImplementedServiceId() {}
	public K2ImplementedServiceId(String organisation, String alias, String serviceAlias) {
		this.organisation = organisation;
		this.alias = alias;
		this.serviceAlias = serviceAlias;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((organisation == null) ? 0 : organisation.hashCode());
		result = prime * result + ((alias == null) ? 0 : alias.hashCode());
		result = prime * result + ((serviceAlias == null) ? 0 : serviceAlias.hashCode());
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
		K2ImplementedServiceId other = (K2ImplementedServiceId) obj;
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
		if (serviceAlias == null) {
			if (other.serviceAlias != null)
				return false;
		} else if (!serviceAlias.equals(other.serviceAlias))
			return false;
		return true;
	}

	

}
