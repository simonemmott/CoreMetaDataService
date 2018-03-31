package com.k2.core.model.types;

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
import com.k2.MetaModel.annotations.MetaEntity;
import com.k2.MetaModel.annotations.MetaType;
import com.k2.MetaModel.annotations.MetaVersion;
import com.k2.core.model.K2Type;
import com.k2.core.model.K2Type.Type;
import com.k2.core.model.types.classes.K2Native;

@MetaVersion(major=0, minor=0, point=1)
@MetaType
@MetaEntity
@Entity
@Table(name="PRIMITIVES")
@DiscriminatorValue("PRIMITIVE")
@PrimaryKeyJoinColumn(name="CLASSNAME", referencedColumnName="CLASSNAME")
public class K2Primitive extends K2Type {

	public K2Primitive() {
		super(); 
	}
	public K2Primitive(long id) {
		super(id); 
		this.setType(K2Type.Type.PRIMITIVE);
	}
	
	// Implementing Native Class ---------------------------------------------
	@Column(name="NATIVECLASSNAME", insertable=false, updatable=false)
	private String nativeClassName;
	@ManyToOne
	@JoinColumn(name="NATIVECLASSNAME", referencedColumnName="CLASSNAME")
	private K2Native implementingNativeClass;
	public K2Native getImplementingNativeClass() { return implementingNativeClass; }
	public void setImplementingNativeClass(K2Native implementingNativeClass) { this.implementingNativeClass = implementingNativeClass; this.nativeClassName = implementingNativeClass.getClassName(); }
	
}
