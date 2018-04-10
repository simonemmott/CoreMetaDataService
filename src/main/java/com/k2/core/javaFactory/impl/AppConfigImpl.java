package com.k2.core.javaFactory.impl;

import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.k2.Wiget.annotation.WigetImplementation;
import com.k2.JavaFactory.JavaFamily;
import com.k2.JavaFactory.AJavaWiget;
import com.k2.JavaFactory.JavaAssembly;
import com.k2.core.javaFactory.spec.AppConfig;
import com.k2.core.model.K2Application;
import com.k2.core.model.K2ImplementedService;
import com.k2.core.model.K2Service;
import com.k2.core.model.K2Version;
import com.k2.Util.classes.ClassUtil;
import com.k2.Wiget.AssembledWiget;
import com.k2.Wiget.Wiget;

@WigetImplementation
public class AppConfigImpl extends AJavaWiget<K2Application> implements AppConfig{
	
	@SuppressWarnings("rawtypes")
	@Override
	public PrintWriter output(
			AssembledWiget<JavaFamily, PrintWriter, ? extends Wiget, K2Application> a,
			PrintWriter out) {
		
		JavaAssembly ta = (JavaAssembly)a.assembly();
		
		String packageName = ClassUtil.getPackageNameFromCanonicalName(a.get(AppConfig.model.configClassName));
		String name = ClassUtil.getBasenameFromCanonicalName(a.get(AppConfig.model.configClassName));
		K2Version version = a.get(AppConfig.model.version);
		
		Set<K2ImplementedService> implementedServices = a.get(AppConfig.model.implementedServices);
		Set<String> implementedServiceClassNames = new HashSet<String>();
		if (implementedServices != null) {
			
			for (K2ImplementedService is : implementedServices) {
				implementedServiceClassNames.add(is.getService().getConfigClassName());
			}
		}
		
		
		out.println("package "+packageName+";");
		out.println("");
		out.println("import com.k2.MetaModel.annotations.MetaApplication;");
		out.println("import com.k2.MetaModel.annotations.MetaVersion;");
		for (String implementedServiceClassName : implementedServiceClassNames)
			if (!ClassUtil.getPackageNameFromCanonicalName(implementedServiceClassName).equals(packageName))
				out.println("import "+implementedServiceClassName+";");			
		out.println("");
		out.println("@MetaApplication(");
		ta.indent();
		out.println(ta.getIndent()+"alias=\""+a.get(AppConfig.model.alias)+"\",");
		out.println(ta.getIndent()+"title=\""+a.get(AppConfig.model.title)+"\",");
		out.println(ta.getIndent()+"description=\""+a.get(AppConfig.model.description)+"\",");
		out.println(ta.getIndent()+"version=@MetaVersion(major="+version.major()+", minor="+version.minor()+", point="+version.point()+", build="+version.getBuildNumber()+"),");
		out.println(ta.getIndent()+"organisation=\""+a.get(AppConfig.model.organisation)+"\",");
		out.println(ta.getIndent()+"website=\""+a.get(AppConfig.model.website)+"\",");
		out.println(ta.getIndent()+"services={");
		ta.indent();
		Iterator<String> i = implementedServiceClassNames.iterator();
		while (i.hasNext()) {
			out.print(ta.getIndent()+ClassUtil.getBasenameFromCanonicalName(i.next())+".class");
			if (i.hasNext())
				out.println(",");
			else
				out.println("");
		}
		ta.outdent();
		out.println(ta.getIndent()+"})");
		ta.outdent();
		out.println("public class "+name+" {");
		out.println("");
		out.println("}");
		
		return out;
	}





	
	

}
