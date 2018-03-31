package com.k2.core.model.types;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.PrimaryKeyJoinColumns;
import javax.persistence.Table;

import com.k2.MetaModel.annotations.MetaType;
import com.k2.MetaModel.annotations.MetaEntity;
import com.k2.MetaModel.annotations.MetaVersion;
import com.k2.core.model.K2Type;
import com.k2.core.model.K2Type.Type;

@MetaVersion(major=0, minor=0, point=1)
@MetaType
@MetaEntity
@Entity
@Table(name="ENUMERATIONS")
@DiscriminatorValue("ENUM")
@PrimaryKeyJoinColumn(name="CLASSNAME", referencedColumnName="CLASSNAME")
public class K2SubType extends K2Type {

	public K2SubType() {
		super(); 
	}
	public K2SubType(long id) {
		super(id); 
		this.setType(K2Type.Type.ENUM);
	}
	
}
