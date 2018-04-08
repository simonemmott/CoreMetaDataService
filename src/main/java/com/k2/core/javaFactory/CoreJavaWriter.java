package com.k2.core.javaFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.invoke.MethodHandles;
import java.nio.file.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.io.Files;
import com.k2.JavaFactory.JavaAssembly;
import com.k2.JavaFactory.JavaFactory;
import com.k2.JavaFactory.JavaOutputLocation;
import com.k2.JavaFactory.JavaWriterException;
import com.k2.Util.FileUtil;
import com.k2.Util.classes.ClassUtil;
import com.k2.Wiget.templateFactory.spec.TemplateImplementation;
import com.k2.Wiget.templateFactory.spec.TemplateSpecification;
import com.k2.Wiget.templateFactory.types.TemplateDef;
import com.k2.core.javaFactory.spec.AppConfig;
import com.k2.core.model.K2Application;

/**
 * The TemplateWriter writes the java source for the template assembly weget specification and implementation classes as defined by the supplied data
 * 
 * @author simon
 *
 */
public class CoreJavaWriter extends JavaOutputLocation {
	
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	private JavaFactory factory = new JavaFactory("com.k2.JavaFactory.impl", "com.k2.core.javaFactory.impl");
	/**
	 * Create a template writer to write the template classes to the given output folder.
	 * Template writers created in this way move current versions of the template classes to an archive folder created in the output folder.
	 * 
	 * @param outputFolder	The output folder in which to write the java source code for the template assembly wigets
	 * @throws JavaWriterException	If there is a problem with the given source location
	 */
	public CoreJavaWriter(String outputPath) throws JavaWriterException {
		super(outputPath);
	}
	
	/**
	 * This service method generates the java source code for the given template assemblies wiget specification and implementation classes in the output location defined when this template writer was instantiated
	 * @param template	The template tofor which to generate and write the java source code
	 * @throws JavaWriterException	If there is a problem writing the java source code to the output location or backing up the existing source for the template wiget to the archive location
	 */
	public void writeAppConfig(K2Application app) throws JavaWriterException {
		
		if (app == null)
			throw new JavaWriterException("Unable to generate source code for the appllication configuration from a null application!");
		
		
		String packageName = ClassUtil.getPackageNameFromCanonicalName(app.getConfigClassName());
		Path packagePath = ClassUtil.packageNameToRelativePath(packageName);
		
		FileUtil.buildTree(outputFolder, packagePath);
		
		File javaResource = outputFolder.toPath().resolve(ClassUtil.packageNameToPath(app.getConfigClassName())+".java").toFile();

		JavaAssembly<AppConfig, TemplateDef> java = factory.getAssembly(AppConfig.class);
		try {
			java.output(app, new PrintWriter(new FileWriter(javaResource))).flush();
		} catch (IOException e) {
			throw new JavaWriterException("Error writing application configuration for {} to {} - {}", e, app.getTitle(), javaResource.getAbsolutePath(), e.getMessage());
		}
					
	}
	
	
	

}
