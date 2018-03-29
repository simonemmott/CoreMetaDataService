package com.k2.core.model.types;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.PrimaryKeyJoinColumns;
import javax.persistence.Table;

import com.k2.MetaModel.annotations.MetaType;
import com.k2.MetaModel.annotations.MetaEntity;
import com.k2.MetaModel.annotations.MetaVersion;
import com.k2.core.model.K2Method;
import com.k2.core.model.K2Service;
import com.k2.core.model.K2Type;
import com.k2.core.model.K2Version;
import com.k2.core.model.K2Type.Type;

import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;

@MetaVersion(major=0, minor=0, point=1)
@MetaType
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

	// K2 Service ---------------------------------------------------------------------------
	@Column(name="SERVICEALIAS", insertable=false, updatable=false)
	private String serviceAlias;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="SERVICEALIAS", referencedColumnName="ALIAS")
	private K2Service k2Service;
	public K2Service getService() { return k2Service; }
	public void setService(K2Service k2Service) { this.k2Service = k2Service; this.serviceAlias = k2Service.getAlias();}
	
	// Methods ----------------------------------------------------------------------------------
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="CLASSNAME", referencedColumnName = "CLASSNAME")
	private Set<K2Method> methods;
	public void setMethods(Set<K2Method> methods) { this.methods = methods; }
	public Set<K2Method> getMethods() { return methods; }

}
