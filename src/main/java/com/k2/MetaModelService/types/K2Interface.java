package com.k2.MetaModelService.types;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.PrimaryKeyJoinColumns;
import javax.persistence.Table;

import com.k2.MetaModel.annotations.MetaClass;
import com.k2.MetaModel.annotations.MetaEntity;
import com.k2.MetaModel.annotations.MetaVersion;

import javax.persistence.JoinColumns;
import javax.persistence.JoinColumn;

@MetaVersion(major=0, minor=0, point=1)
@MetaClass
@MetaEntity
@Entity
@Table(name="INTERFACES")
@DiscriminatorValue("INTERFACE")
@PrimaryKeyJoinColumn(name="CLASSNAME", referencedColumnName="CLASSNAME")
public class K2Interface extends K2Type {

	public K2Interface() {
		super(); 
	}
	public K2Interface(String className) {
		super(className); 
		this.setType(K2Type.Type.INTERFACE);
	}
	
	// Version -------------------------------------------------------------------------
	@Embedded
	private K2Version version;
	public K2Version getVersion() { return version; }
	public void setVersion(K2Version version) { this.version = version; }
	
	// Methods ----------------------------------------------------------------------------------
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="CLASSNAME", referencedColumnName = "CLASSNAME")
	private Set<K2Method> methods;
	public void setMethods(Set<K2Method> methods) { this.methods = methods; }
	public Set<K2Method> getMethods() { return methods; }

}
