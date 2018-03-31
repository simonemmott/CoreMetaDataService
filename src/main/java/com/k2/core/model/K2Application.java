package com.k2.core.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.k2.MetaModel.annotations.MetaType;
import com.k2.MetaModel.annotations.MetaEntity;
import com.k2.MetaModel.annotations.MetaField;
import com.k2.MetaModel.annotations.MetaOwningSet;
import com.k2.MetaModel.annotations.MetaVersion;
import com.k2.Service.service.LinkValues;
import com.k2.Service.service.ServiceManager;

@MetaVersion(major=0, minor=0, point=1)
@MetaType
@MetaEntity
@Entity
@Table(name="APPLICATIONS")
@IdClass(K2ApplicationId.class)
public class K2Application {
	
	private ServiceManager serviceManager;
	public void setServiceManager(ServiceManager serviceManager) { this.serviceManager = serviceManager; }
	
	public K2Application() {}
	public K2Application(String organisation, String alias) {
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
	
	// Title ------------------------------------------------------------------------------
	@MetaField
	@Column(name="TITLE", nullable=true, length=128)
	private String title;
	public String getTitle() { return title; }
	public void setTitle(String title) { this.title = title; }
	
	// Description ------------------------------------------------------------------------------
	@MetaField
	@Column(name="DESCRIPTION", nullable=true, length=4000)
	private String description;
	public String getDescription() { return description; }
	public void setDescription(String description) { this.description = description; }
	
	// Version -------------------------------------------------------------------------
	@Embedded
	private K2Version version;
	public K2Version getVersion() { return version; }
	public void setVersion(K2Version version) { this.version = version; }
	
	// Website ------------------------------------------------------------------------------
	@MetaField
	@Column(name="WEBSITE", nullable=true, length=128)
	private String website;
	public String getWebsite() { return website; }
	public void setWebsite(String website) { this.website = website; }
	
	// Implemented Services ----------------------------------------------------------------------------------
	@MetaOwningSet
	@OneToMany(cascade=CascadeType.ALL)
    @JoinColumns({
		@JoinColumn(name="ORGANISATION", referencedColumnName = "ORGANISATION"),
		@JoinColumn(name="ALIAS", referencedColumnName = "ALIAS")
    	})
	private Set<K2ImplementedService> implementedServices;
	public void setImplementedServices(Set<K2ImplementedService> implementedServices) { this.implementedServices = implementedServices; }
	public Set<K2ImplementedService> getImplementedServices() { return implementedServices; }
	public K2ImplementedService newItemInImplementedServices() {
		K2ImplementedService item = serviceManager.newEntity(K2ImplementedService.class, new LinkValues<K2ImplementedService>() {
			@Override public void populate(K2ImplementedService item) {
				item.setOrganisation(organisation);
				item.setAlias(alias);
			}
		});
		if (implementedServices == null) implementedServices = new HashSet<K2ImplementedService>();
		implementedServices.add(item);
		return item;
	}


}
