package com.k2.MetaModelService.metadata;

import java.lang.invoke.MethodHandles;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.k2.MetaModel.MetaModel;
import com.k2.MetaModel.MetaModelService;
import com.k2.MetaModelService.types.*;
import com.k2.Util.Version.Version;

public class MetaData {

	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	public static MetaData fromMetaModel(MetaModel metaModel) {
		return new MetaData(metaModel);
	}
	
	private MetaModel metaModel;
	
	public MetaModel getMetaModel() { return metaModel; }
	
	private Map<String, K2Type> allTypes = new HashMap<String, K2Type>();
	public Set<K2Type> getAllTypes() {
		Set<K2Type> set = new TreeSet<K2Type>();
		set.addAll(allTypes.values());
		return set;
	}
	public K2Type getType(String name) {
		K2Type t = allTypes.get(name);
		if (t != null)
			return t;
		throw new MetaDataError("No type defined with name {}", name);
	}
	K2Type registerType(K2Type type) {
		if (allTypes.containsKey(type.getClassName())) {
			logger.warn("A type named {} has already been registed. The given type will be ignored!", type.getClassName());
			return allTypes.get(type.getClassName());
		}
		allTypes.put(type.getClassName(), type);
		if (type instanceof K2Primitive)
			primitives.put(type.getClassName(), (K2Primitive)type);
		if (type instanceof K2Class)
			classes.put(type.getClassName(), (K2Class)type);
		if (type instanceof K2SubType)
			subTypes.put(type.getClassName(), (K2SubType)type);
		if (type instanceof K2Interface)
			interfaces.put(type.getClassName(), (K2Interface)type);
		if (type instanceof K2Native)
			natives.put(type.getClassName(), (K2Native)type);
		if (type instanceof K2Entity)
			entities.put(type.getClassName(), (K2Entity)type);
		if (type instanceof K2Transient)
			transients.put(type.getClassName(), (K2Transient)type);
		if (type instanceof K2Embeddable)
			embeddables.put(type.getClassName(), (K2Embeddable)type);
		
		
		
		return type;
	}
	
	
	private Map<String, K2Primitive> primitives = new HashMap<String,K2Primitive>();
	public Set<K2Primitive> getPrimitives() { 
		Set<K2Primitive> set = new TreeSet<K2Primitive>();
		set.addAll(primitives.values());
		return set;
	}
	public K2Primitive getPrimitive(String name) {
		K2Primitive p = primitives.get(name);
		if (p != null)
			return p;
		throw new MetaDataError("No primitive defined with name {}", name);
	}
	
	private Map<String, K2Class> classes = new HashMap<String,K2Class>();
	public Set<K2Class> getClasses() { 
		Set<K2Class> set = new TreeSet<K2Class>();
		set.addAll(classes.values());
		return set;
	}
	public K2Class getK2Class(String name) {
		K2Class p = classes.get(name);
		if (p != null)
			return p;
		throw new MetaDataError("No class defined with name {}", name);
	}
	
	private Map<String, K2SubType> subTypes = new HashMap<String,K2SubType>();
	public Set<K2SubType> getSubTypes() { 
		Set<K2SubType> set = new TreeSet<K2SubType>();
		set.addAll(subTypes.values());
		return set;
	}
	public K2SubType getSubType(String name) {
		K2SubType p = subTypes.get(name);
		if (p != null)
			return p;
		throw new MetaDataError("No enumeration defined with name {}", name);
	}
	
	private Map<String, K2Interface> interfaces = new HashMap<String,K2Interface>();
	public Set<K2Interface> getInterfaces() { 
		Set<K2Interface> set = new TreeSet<K2Interface>();
		set.addAll(interfaces.values());
		return set;
	}
	public K2Interface getInterface(String name) {
		K2Interface p = interfaces.get(name);
		if (p != null)
			return p;
		throw new MetaDataError("No interface defined with name {}", name);
	}
	
	private Map<String, K2Native> natives = new HashMap<String,K2Native>();
	public Set<K2Native> getNatives() { 
		Set<K2Native> set = new TreeSet<K2Native>();
		set.addAll(natives.values());
		return set;
	}
	public K2Native getNative(String name) {
		K2Native n = natives.get(name);
		if (n != null)
			return n;
		throw new MetaDataError("No native defined with name {}", name);
	}
	
	private Map<String, K2Entity> entities = new HashMap<String,K2Entity>();
	public Set<K2Entity> getEntities() { 
		Set<K2Entity> set = new TreeSet<K2Entity>();
		set.addAll(entities.values());
		return set;
	}
	public K2Entity getEntity(String name) {
		K2Entity n = entities.get(name);
		if (n != null)
			return n;
		throw new MetaDataError("No entity defined with name {}", name);
	}
	
	private Map<String, K2Transient> transients = new HashMap<String,K2Transient>();
	public Set<K2Transient> getTransients() { 
		Set<K2Transient> set = new TreeSet<K2Transient>();
		set.addAll(transients.values());
		return set;
	}
	public K2Transient getTransient(String name) {
		K2Transient n = transients.get(name);
		if (n != null)
			return n;
		throw new MetaDataError("No native defined with name {}", name);
	}
	
	private Map<String, K2Embeddable> embeddables = new HashMap<String,K2Embeddable>();
	public Set<K2Embeddable> getEmbeddables() { 
		Set<K2Embeddable> set = new TreeSet<K2Embeddable>();
		set.addAll(embeddables.values());
		return set;
	}
	public K2Embeddable getEmbeddable(String name) {
		K2Embeddable n = embeddables.get(name);
		if (n != null)
			return n;
		throw new MetaDataError("No embeddable defined with name {}", name);
	}
	
	
	private K2Native newNative(String name, String description) {
		K2Native n = new K2Native(name);
		n.setType(K2Type.Type.CLASS);
		n.setClassType(K2Class.ClassType.NATIVE);
		n.setDescription(description);
		return n;
	}
	
	private K2Primitive newPrimitive(String name, String description, K2Native implementingNative) {
		K2Primitive p = new K2Primitive(name);
		p.setType(K2Type.Type.PRIMITIVE);
		p.setDescription(description);
		p.setImplementingNativeClass(implementingNative);
		return p;
	}
	
	private void registerPrimitiveAndNative(String primitiveName, String nativeName, String description) {

		K2Native n = newNative(nativeName, description);
		natives.put(n.getClassName(), n);
		classes.put(n.getClassName(), n);
		allTypes.put(n.getClassName(), n);
		K2Primitive p = newPrimitive(primitiveName, description, n);
		primitives.put(p.getClassName(), p);
		allTypes.put(p.getClassName(), p);
	}
	
	private void registerNative(String name, String description) {
		K2Native n = newNative(name, description);
		natives.put(n.getClassName(), n);
		classes.put(n.getClassName(), n);
		allTypes.put(n.getClassName(), n);
	}
	
	private Map<String, K2Service> allServices = new HashMap<String, K2Service>();
	private Map<String, K2Service> implementedServices = new HashMap<String, K2Service>();
	private Map<String, K2Service> requiredServices = new HashMap<String, K2Service>();
	private K2Service registerImplementedService(K2Service k2Service) {
		allServices.put(k2Service.getAlias(), k2Service);
		implementedServices.put(k2Service.getAlias(), k2Service);
		return k2Service;
	}
	private K2Service registerRequiredService(K2Service k2Service) {
		allServices.put(k2Service.getAlias(), k2Service);
		requiredServices.put(k2Service.getAlias(), k2Service);
		return k2Service;
	}
	public Set<K2Service> getImplementedServices() {
		Set<K2Service> set = new HashSet<K2Service>();
		set.addAll(implementedServices.values());
		return set;
	}
	public Set<K2Service> getRequiredServices() {
		Set<K2Service> set = new HashSet<K2Service>();
		set.addAll(requiredServices.values());
		return set;
	}
	public K2Service getService(String alias) {
		K2Service s = allServices.get(alias);
		if (s != null)
			return s;
		throw new MetaDataError("No service with alias {} is registerd in this metadata", alias);
	}
	
	private MetaData(MetaModel metaModel) {
		this.metaModel = metaModel;
		
		registerPrimitiveAndNative("int", "java.lang.Integer", "Integer values");
		registerPrimitiveAndNative("long", "java.lang.Long", "Long values");
		registerPrimitiveAndNative("float", "java.lang.Float", "Floating point values");
		registerPrimitiveAndNative("double", "java.lang.Double", "Double precision floating point values");
		registerPrimitiveAndNative("boolean", "java.lang.Boolean", "Boolean values");
		registerPrimitiveAndNative("short", "java.lang.Short", "Short integer values");
		registerPrimitiveAndNative("char", "java.lang.Character", "Character values");
		registerPrimitiveAndNative("byte", "java.lang.Byte", "Byte values");
		
		registerNative("java.lang.String", "String values");
		registerNative("java.util.Date", "Date values");
		
		for (MetaModelService mms : metaModel.implementedServices()) {
			registerImplementedService(MetaDataService.toData(this, mms));
		}
		
	}
	
	
	
}
