package com.k2.MetaModelService.types;

import javax.persistence.Column;
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
import com.k2.MetaModel.annotations.MetaVersion;

@MetaVersion(major=0, minor=0, point=1)
@MetaType
@MetaEntity
@Entity
@Table(name="FIELDS")
@IdClass(K2FieldId.class)
public class K2Field {
	
	public K2Field() {}
	public K2Field(String className, String name) {
		this.className = className;
		this.name = name;
	}
	
	// Name ------------------------------------------------------------------------------
	@Id
	@Column(name="CLASSNAME", nullable=false, length=256)
	private String className;
	public String getClassName() { return className; }
	public void setClassName(String className) { this.className = className; }
	
	// Name ------------------------------------------------------------------------------
	@Id
	@Column(name="NAME", nullable=false)
	private String name;
	public String getName() { return name; }
	public void seName(String name) { this.name = name; }
	
	// Label ------------------------------------------------------------------------------
	@Column(name="LABEL", nullable=true, length=128)
	private String label;
	public String getLabel() { return label; }
	public void setLabel(String label) { this.label = label; }
	
	// Description ------------------------------------------------------------------------------
	@Column(name="DESCRIPTION", nullable=true, length=4000)
	private String description;
	public String getDescription() { return description; }
	public void setDescription(String description) { this.description = description; }
	
	// Declaring Type ------------------------------------------------------------------------------
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="CLASSNAME", referencedColumnName="CLASSNAME")
	private K2Type declaringType;
	public K2Type getDeclaringType() { return declaringType; }

	// Data Type -----------------------------------------------------------------------
	@Column(name="DATATYPENAME", updatable=false)
	private String dataTypeName;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="DATATYPENAME", referencedColumnName="NAME")
	private K2Type dataType;
	public K2Type getDataType() { return dataType; }
	public void setDataType(K2Type dataType) { this.dataType = dataType; this.dataTypeName = dataType.getClassName(); }

}
