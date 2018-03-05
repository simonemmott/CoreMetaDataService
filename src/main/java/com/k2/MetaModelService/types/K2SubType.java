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
@Table(name="ENUMERATIONS")
@DiscriminatorValue("ENUM")
@PrimaryKeyJoinColumn(name="CLASSNAME", referencedColumnName="CLASSNAME")
public class K2SubType extends K2Type {

	public K2SubType() {
		super(); 
	}
	public K2SubType(String className) {
		super(className); 
		this.setType(K2Type.Type.ENUM);
	}
	
}
