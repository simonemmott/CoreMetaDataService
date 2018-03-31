package com.k2.core.model.types.classes;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.PrimaryKeyJoinColumns;
import javax.persistence.Table;

import com.k2.MetaModel.annotations.MetaType;
import com.k2.MetaModel.annotations.MetaEntity;
import com.k2.MetaModel.annotations.MetaVersion;
import com.k2.core.model.K2Version;
import com.k2.core.model.types.K2Class;

@MetaVersion(major=0, minor=0, point=1)
@MetaType
@MetaEntity
@Entity
@Table(name="ENTITIES")
@DiscriminatorValue("ENTITY")
@PrimaryKeyJoinColumn(name="CLASSNAME", referencedColumnName="CLASSNAME")
public class K2Entity extends K2Class {
	
	public K2Entity() {
		super(); 
	}
	public K2Entity(long id) {
		super(id); 
	}
	
	// Entity Name ----------------------------------------------------------------------------
	@Column(name="ENTITYNAME", nullable=false)
	private String entityName;
	public String getEntityName() { return entityName; }
	public void setEntityName(String entityName) { this.entityName = entityName; }
	
	// Table Name ------------------------------------------------------------------------------
	@Column(name="TABLENAME", nullable=false)
	private String tableName;
	public String getTableName() { return tableName; }
	public void setTableName(String tableName) { this.tableName = tableName; }
	
	// Version -------------------------------------------------------------------------
	@Embedded
	private K2Version version;
	public K2Version getVersion() { return version; }
	public void setVersion(K2Version version) { this.version = version; }
	

	
}
