package com.k2.MetaModelService.types;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.PrimaryKeyJoinColumns;
import javax.persistence.Table;

import com.k2.MetaModel.annotations.MetaClass;
import com.k2.MetaModel.annotations.MetaEntity;
import com.k2.MetaModel.annotations.MetaVersion;

@MetaVersion(major=0, minor=0, point=1)
@MetaClass
@MetaEntity
@Entity
@Table(name="NATIVES")
@DiscriminatorValue("NATIVE")
@PrimaryKeyJoinColumn(name="CLASSNAME", referencedColumnName="CLASSNAME")
public class K2Native extends K2Class{

	public K2Native() {
		super(); 
	}
	public K2Native(String className) {
		super(className); 
		this.setClassType(K2Class.ClassType.NATIVE);
	}
	
	
}
