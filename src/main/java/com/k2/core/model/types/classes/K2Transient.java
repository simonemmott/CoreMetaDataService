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
import com.k2.core.model.K2Version;
import com.k2.core.model.types.K2Class;
import com.k2.MetaModel.annotations.MetaEntity;

@MetaType
@MetaEntity
@Entity
@Table(name="TRANSIENTS")
@DiscriminatorValue("TRANSIENT")
@PrimaryKeyJoinColumn(name="CLASSNAME", referencedColumnName="CLASSNAME")
public class K2Transient extends K2Class {


	public K2Transient() {
		super(); 
	}
	public K2Transient(long id) {
		super(id); 
		this.setClassType(K2Class.ClassType.TRANSIENT);
	}
	
	// Version -------------------------------------------------------------------------
	@Embedded
	private K2Version version;
	public K2Version getVersion() { return version; }
	public void setVersion(K2Version version) { this.version = version; }
	

}
