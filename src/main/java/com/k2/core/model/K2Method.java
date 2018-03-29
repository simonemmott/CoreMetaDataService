package com.k2.core.model;

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
@Table(name="METHODS")
@IdClass(K2MethodId.class)
public class K2Method {

	
	public K2Method() {}
	public K2Method(String className, String name) {
		this.className = className;
		this.name = name;
	}
	
	// Name ------------------------------------------------------------------------------
	@Id
	@Column(name="CLASSNAME", nullable=false, length=256)
	private String className;
	public String getClassName() { return className; }
	public void setClassName(String className) { this.className = className; }
	
	// Method Id ------------------------------------------------------------------------------
	@Id
	@Column(name="METHODID", nullable=false)
	private Long methodId;
	public Long getMethodId() { return methodId; }
	public void setMethodId(Long methodId) { this.methodId = methodId; }
	
	// Method Name ------------------------------------------------------------------------------
	@Column(name="NAME", nullable=false)
	private String name;
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }
	
	// Label ------------------------------------------------------------------------------
	@Column(name="LABEL", nullable=true, length=128)
	private String label;
	public String getLabel() { return label; }
	public void setLabel(String label) { this.label = label; }
	
	// Description ------------------------------------------------------------------------------
	@Column(name="DESCRIPTION", nullable=true, length=4000)
	private String desciption;
	public String getDescription() { return desciption; }
	public void setDescription(String desciption) { this.desciption = desciption; }
	
	// Declaring Type ------------------------------------------------------------------------------
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="CLASSNAME", referencedColumnName="CLASSNAME")
	private K2Type declaringType;
	public K2Type getDeclaringType() { return declaringType; }

	// Return Type -----------------------------------------------------------------------
	@Column(name="RETURNTYPENAME", updatable=false)
	private String returnTypeName;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="RETURNTYPENAME", referencedColumnName="NAME")
	private K2Type returnType;
	public K2Type getReturnType() { return returnType; }
	public void setReturnType(K2Type returnType) { this.returnType = returnType; this.returnTypeName = returnType.getClassName(); }

}
