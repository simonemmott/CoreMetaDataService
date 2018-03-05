package com.k2.MetaModelService.types;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Embedded;

import com.k2.MetaModel.TypeValue;
import com.k2.MetaModel.annotations.MetaClass;
import com.k2.MetaModel.annotations.MetaEntity;
import com.k2.MetaModel.annotations.MetaSubType;
import com.k2.MetaModel.annotations.MetaVersion;

@MetaVersion(major=0, minor=0, point=1)
@MetaClass
@MetaEntity
@Entity
@Table(name="TYPES")
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name="K2TYPE")
public class K2Type  implements Comparable<K2Type> {

	@Override
	public int compareTo(K2Type o) {
		return (type+":"+className).compareTo(o.type+":"+o.className); 
	}

	@MetaSubType(description="The top level types of the basic type class")
	public enum Type implements TypeValue {
		PRIMITIVE("PRIMITIVE", "Primitive type", "Primitive values like int, long, char etc."),
		CLASS("NATIVE", "Native type", "Native values are types that exist natively in the java language"),
		ENUM("ENUM", "Sub type", "Sub types define sub categorisation of an entity or transient type"),
		INTERFACE("INTERFACE", "Interface type", "Interfaces define a set of publicly available methods for interacting with a type");
		
		private String alias;
		private String title;
		private String description;
		
		Type(String alias, String title, String description) {
			this.alias = alias;
			this.title = title;
			this.description = description;
		}

		@Override public String alias() { return alias; }
		@Override public String title() { return title; }
		@Override public String description() { return description; }
	}
	
	public K2Type() {}
	public K2Type(String className) {
		this.className = className; 
	}
	
	// Name ------------------------------------------------------------------------------
	@Id
	@Column(name="CLASSNAME", nullable=false)
	private String className;
	public String getClassName() { return className; }
	public void setClassName(String className) { this.className = className; }
	
	// K2 Type ---------------------------------------------------------------------------
	@Column(name="K2TYPE", nullable=false)
	@Enumerated(EnumType.STRING)
	private Type type;
	public Type getType() { return type; }
	public void setType(Type type) { this.type = type; }

	// Description ------------------------------------------------------------------------------
	@Column(name="DESCRIPTION", nullable=true, length=4000)
	private String description;
	public String getDescription() { return description; }
	public void setDescription(String description) { this.description = description; }
	


	

}