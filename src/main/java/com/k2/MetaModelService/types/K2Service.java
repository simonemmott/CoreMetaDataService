package com.k2.MetaModelService.types;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.k2.MetaModel.annotations.MetaType;
import com.k2.MetaModel.annotations.MetaEntity;
import com.k2.MetaModel.annotations.MetaVersion;

@MetaVersion(major=0, minor=0, point=1)
@MetaType
@MetaEntity
@Entity
@Table(name="SERVICES")
public class K2Service {

	public K2Service() {}
	public K2Service(String alias) {
		this.alias = alias;
	}

	
	
	// Alias --------------------------------------------------------------------------
	@Id
	@Column(name="ALIAS", nullable=false)
	private String alias;
	public String getAlias() { return alias; }
	public void setAlias(String alias) { this.alias = alias; }
	
	// Title ---------------------------------------------------------------------------
	@Column(name="TITLE", nullable=false)
	private String title;
	public String getTitle() { return title; }
	public void setTitle(String title) { this.title = title; }
	
	// Version -------------------------------------------------------------------------
	@Embedded
	private K2Version version;
	public K2Version getVersion() { return version; }
	public void setVersion(K2Version version) { this.version = version; }
	
	// Description ----------------------------------------------------------------------
	@Column(name="DESCRIPTION", nullable=true)
	private String description;
	public String getDescription() { return description; }
	public void setDescription(String description) { this.description = description; }
	
	// All Managed Types ----------------------------------------------------------------
	private List<K2Type> allManagedTypes;
	public List<K2Type> getAllManagedTypes() { return allManagedTypes; }
	public void setAllManagedTypes(List<K2Type> allManagedTypes) { this.allManagedTypes = allManagedTypes; }
	
	// Managed Entities -----------------------------------------------------------------
	private List<K2Entity> managedEntities;
	public List<K2Entity> getManagedEntities() { return managedEntities; }
	public void setManagedEntities(List<K2Entity> managedEntities) { this.managedEntities = managedEntities; }
	
	// Managed Embeddables -----------------------------------------------------------------
	private List<K2Embeddable> managedEmbeddables;
	public List<K2Embeddable> getManagedEmbeddables() { return managedEmbeddables; }
	public void setManagedEmbeddables(List<K2Embeddable> managedEmbeddables) { this.managedEmbeddables = managedEmbeddables; }
	
	// Managed Transients -----------------------------------------------------------------
	private List<K2Transient> managedTransients;
	public List<K2Transient> getManagedTransients() { return managedTransients; }
	public void setManagedTransients(List<K2Transient> managedTransients) { this.managedTransients = managedTransients; }
	
	
	
	
	
}
