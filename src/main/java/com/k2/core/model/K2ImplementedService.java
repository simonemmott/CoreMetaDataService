package com.k2.core.model;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.k2.MetaModel.annotations.MetaType;
import com.k2.MetaModel.annotations.MetaEntity;
import com.k2.MetaModel.annotations.MetaField;
import com.k2.MetaModel.annotations.MetaOwningSet;
import com.k2.MetaModel.annotations.MetaVersion;
import com.k2.Service.service.ServiceManager;

@MetaVersion(major=0, minor=0, point=1)
@MetaType
@MetaEntity
@Entity
@MetaOwningSet(owningClass=K2Application.class, name="implementedServices")
@Table(name="IMPLEMENTEDSERVICES")
@IdClass(K2ImplementedServiceId.class)
public class K2ImplementedService {
	
	protected ServiceManager serviceManager;
	public void setServiceManager(ServiceManager serviceManager) { this.serviceManager = serviceManager; }

	public K2ImplementedService() {}
	public K2ImplementedService(String organisation, String alias) {
		this.organisation = organisation;
		this.alias = alias;
	}
	
	// Organisation ------------------------------------------------------------------------------
	@MetaField
	@Id
	@Column(name="ORGANISATION", nullable=false, length=256)
	private String organisation;
	public String getOrganisation() { return organisation; }
	public void setOrganisation(String organisation) { this.organisation = organisation; }
	
	// Alias ------------------------------------------------------------------------------
	@MetaField
	@Id
	@Column(name="ALIAS", nullable=false, length=128)
	private String alias;
	public String getAlias() { return alias; }
	public void setAlias(String alias) { this.alias = alias; }
	
	// Service Alias ------------------------------------------------------------------------------
	@MetaField
	@Id
	@Column(name="SERVICEALIAS", nullable=true, length=128)
	private String serviceAlias;
	public String getServiceAlias() { return serviceAlias; }
	public void setServiceAlias(String serviceAlias) { this.serviceAlias = serviceAlias; }
	
	// Service - Dynamic Link --------------------------------------------------------------------
	public K2Service getService() { return serviceManager.find(K2Service.class, serviceAlias); }


}
