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
@Table(name="EMBEDDABLES")
@DiscriminatorValue("EMBEDDABLE")
@PrimaryKeyJoinColumn(name="CLASSNAME", referencedColumnName="CLASSNAME")
public class K2Embeddable extends K2Class {
	
	public K2Embeddable() {
		super(); 
	}
	public K2Embeddable(long id) {
		super(id); 
		this.setClassType(K2Class.ClassType.EMBEDDABLE);
	}
	
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
