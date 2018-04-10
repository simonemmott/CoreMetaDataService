package com.k2.core.javaFactory;

import com.k2.JavaFactory.JavaFactory;
import com.k2.JavaFactory.type.impl.AnnotationImpl;
import com.k2.JavaFactory.type.impl.TypeImpl;

public class Meta {

	public static AnnotationImpl metaApplication() {
		return new AnnotationImpl("com.k2.MetaModel.annotations.MetaApplication")
				.define(JavaFactory.String, "alias", false).up(AnnotationImpl.class)
				.define(JavaFactory.String, "title", false).up(AnnotationImpl.class)
				.define(JavaFactory.String, "description", false).up(AnnotationImpl.class)
				.define(JavaFactory.Annotation, "version", false).up(AnnotationImpl.class)
				.define(JavaFactory.String, "organisation", false).up(AnnotationImpl.class)
				.define(JavaFactory.String, "website", false).up(AnnotationImpl.class)
				.define(JavaFactory.Class, "services", true).up(AnnotationImpl.class);
	}
	
	public static AnnotationImpl metaService() {
		return new AnnotationImpl("com.k2.MetaModel.annotations.MetaService")
				.define(JavaFactory.String, "alias", false).up(AnnotationImpl.class)
				.define(JavaFactory.String, "title", false).up(AnnotationImpl.class)
				.define(JavaFactory.String, "description", false).up(AnnotationImpl.class)
				.define(JavaFactory.Annotation, "version", false).up(AnnotationImpl.class)
				.define(JavaFactory.String, "modelPackageNames", true).up(AnnotationImpl.class)
				.define(JavaFactory.Class, "serviceInterface", false).up(AnnotationImpl.class)
				.define(JavaFactory.Class, "serviceImplementation", false).up(AnnotationImpl.class);
	}
	
	public static AnnotationImpl metaVersion() {
		return new AnnotationImpl("com.k2.MetaModel.annotations.MetaVersion")
				.define(JavaFactory.INT, "major", false).up(AnnotationImpl.class)
				.define(JavaFactory.INT, "minor", false).up(AnnotationImpl.class)
				.define(JavaFactory.INT, "point", false).up(AnnotationImpl.class)
				.define(JavaFactory.INT, "build", false).up(AnnotationImpl.class);
	}
	
	public static AnnotationImpl configClass() {
		return new AnnotationImpl("com.k2.ConfigClass.ConfigClass")
				.define(JavaFactory.String, "filename", false).up(AnnotationImpl.class)
				.define(new TypeImpl("com.k2.ConfigClass.ConfigLocation"), "location", false).up(AnnotationImpl.class)
				.define(JavaFactory.String, "dateFormat", false).up(AnnotationImpl.class);
	}
	
}
