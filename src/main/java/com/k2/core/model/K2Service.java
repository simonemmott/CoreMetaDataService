package com.k2.core.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.k2.MetaModel.annotations.MetaType;
import com.k2.MetaModel.annotations.MetaEntity;
import com.k2.MetaModel.annotations.MetaField;
import com.k2.MetaModel.annotations.MetaList;
import com.k2.MetaModel.annotations.MetaVersion;
import com.k2.Service.dataAccess.K2Dao;
import com.k2.Service.service.ServiceManager;
import com.k2.core.model.types.classes.K2Embeddable;
import com.k2.core.model.types.classes.K2Entity;
import com.k2.core.model.types.classes.K2Transient;

@MetaVersion(major=0, minor=0, point=1)
@MetaType
@MetaEntity
@Entity
@Table(name="SERVICES")
public class K2Service {

	protected ServiceManager serviceManager;
	public void setServiceManager(ServiceManager serviceManager) { this.serviceManager = serviceManager; }

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
	
	// Config Class Name ------------------------------------------------------------------------------
	@MetaField
	@Column(name="CONFIGCLASSNAME", nullable=true, length=128)
	private String configClassName;
	public String getConfigClassName() { return configClassName; }
	public void setConfigClassName(String configClassName) { this.configClassName = configClassName; }
	
	// Service Interface Name ------------------------------------------------------------------------------
	@MetaField
	@Column(name="SERVICEINTERFACENAME", nullable=true, length=128)
	private String serviceInterfaceName;
	public String getServiceInterfaceName() { return serviceInterfaceName; }
	public void setServiceInterfaceName(String serviceInterfaceName) { this.serviceInterfaceName = serviceInterfaceName; }
	
	// Service Implementation Class Name ------------------------------------------------------------------------------
	@MetaField
	@Column(name="SERVICEIMPLEMENTATIONCLASSNAME", nullable=true, length=128)
	private String serviceImplementationClassName;
	public String getServiceImplementationClassName() { return serviceImplementationClassName; }
	public void setServiceImplementationClassName(String serviceImplementationClassName) { this.serviceImplementationClassName = serviceImplementationClassName; }
	
	// Service Config File Name ------------------------------------------------------------------------------
	@MetaField
	@Id
	@Column(name="CONFIGFILENAME", nullable=true, length=128)
	private String configFileName;
	public String getConfigFileName() { return configFileName; }
	public void setConfigFileName(String configFileName) { this.configFileName = configFileName; }
	
	// All Managed Types ----------------------------------------------------------------
	@MetaList(criteriaAlias="ManagedTypesForService")
	private List<K2Type> allManagedTypes;
	public List<K2Type> getAllManagedTypes() { 
		if (allManagedTypes != null)
			return allManagedTypes;
		K2Dao<K2Type, String> dao = serviceManager.getDaoFactory().getDao(K2Type.class, String.class);
		allManagedTypes = dao.list()
		return allManagedTypes; 
	}
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
