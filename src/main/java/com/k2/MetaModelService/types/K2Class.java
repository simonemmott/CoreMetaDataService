package com.k2.MetaModelService.types;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.PrimaryKeyJoinColumns;
import javax.persistence.Table;

import com.k2.MetaModel.TypeValue;
import com.k2.MetaModel.annotations.MetaType;
import com.k2.MetaModel.annotations.MetaEntity;
import com.k2.MetaModel.annotations.MetaSubType;
import com.k2.MetaModel.annotations.MetaVersion;

@MetaVersion(major=0, minor=0, point=1)
@MetaType
@MetaEntity
@Entity
@Table(name="CLASSES")
@DiscriminatorValue("CLASS")
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name="CLASSTYPE")
@PrimaryKeyJoinColumn(name="CLASSNAME", referencedColumnName="CLASSNAME")
public class K2Class extends K2Type {

	@MetaType(title="Class Types", description="The subtypes of the class type")
	@MetaSubType
	public enum ClassType implements TypeValue {
		NATIVE("NATIVE", "Native type", "Native types like String, Long, Date etc."),
		ENTITY("ENTITY", "Entity type", "Entity types are classes that can be persisted using the Java Persistence API, JPA"),
		TRANSIENT("TRANSIENT", "Transient type", "Transient types are classes that cannot be persisted using the Java Persistence API"), 
		EMBEDDABLE("EMBEDDABLE", "Embeddable type", "Embeddable types are classes that can be embedded in other entity classes and persisted as their containing class is persisted using the Java Persistence API");
		
		private String alias;
		private String title;
		private String description;
		
		ClassType(String alias, String title, String description) {
			this.alias = alias;
			this.title = title;
			this.description = description;
		}

		@Override public String alias() { return alias; }
		@Override public String title() { return title; }
		@Override public String description() { return description; }
	}

	public K2Class() {
		super(); 
	}
	public K2Class(String className) {
		super(className);
		this.setType(K2Type.Type.CLASS);
	}
	
	// Class Type -------------------------------------------------------------------------
	@Column(name="CLASSTYPE", nullable=false)
	@Enumerated(EnumType.STRING)
	private ClassType classType;
	public ClassType getClassType() { return classType; }
	public void setClassType(ClassType classType) { this.classType = classType; }

	// K2 Service ---------------------------------------------------------------------------
	@Column(name="SERVICEALIAS", insertable=false, updatable=false)
	private String serviceAlias;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="SERVICEALIAS", referencedColumnName="ALIAS")
	private K2Service k2Service;
	public K2Service getService() { return k2Service; }
	public void setService(K2Service k2Service) { this.k2Service = k2Service; this.serviceAlias = k2Service.getAlias();}
	
	// Alias ----------------------------------------------------------------------------------
	@Column(name="ALIAS", nullable=false, length=128)
	private String alias;
	public String getAlias() { return alias; }
	public void setAlias(String alias) { this.alias = alias; }
	
	// Title ----------------------------------------------------------------------------------
	@Column(name="TITLE", nullable=false, length=128)
	private String title;
	public String getTitle() { return title; }
	public void setTitle(String title) { this.title = title; }
	
	// Fields ----------------------------------------------------------------------------------
	@OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="CLASSNAME", referencedColumnName = "CLASSNAME")
	private Set<K2Field> fields;
	public void setFields(Set<K2Field> fields) { this.fields = fields; }
	public Set<K2Field> getFields() { return fields; }

	// Methods ----------------------------------------------------------------------------------
	@OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="CLASSNAME", referencedColumnName = "CLASSNAME")
	private Set<K2Method> methods;
	public void setMethods(Set<K2Method> methods) { this.methods = methods; }
	public Set<K2Method> getMethods() { return methods; }
	
	// Declared Sub Types -----------------------------------------------------------------
	private List<K2SubType> declaredSubTypes;
	public List<K2SubType> getDeclaredSubTypes() { return declaredSubTypes; }
	public void setDeclaredSubTypes(List<K2SubType> declaredSubTypes) { this.declaredSubTypes = declaredSubTypes; }


}
