package com.coll.config;

import java.nio.charset.StandardCharsets;

import javax.servlet.ServletRegistration;
import javax.servlet.Filter;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.DBConfig.DBConfig;

public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer
	{
		@Override
		protected void customizeRegistration(ServletRegistration.Dynamic registration) {
			System.out.println("customizeRegistration");
			registration.setInitParameter("dispatchOptionsRequest", "true");
			registration.setAsyncSupported(true);
		}
		
	//========================================ROOT CONFIG CLASSES==================================================================
	
	
	@Override
	protected Class<?>[] getRootConfigClasses() {
		
		System.out.println("Root config classes method");
		return new Class[] {WebResolver.class, DBConfig.class};
	}

	
	//========================================SERVLET CONFIG CLASSESS==============================================================
	
	
	@Override
	protected Class<?>[] getServletConfigClasses() {
		System.out.println("Server Config Classes Method");
		return null;
	}
	
	
	//========================================SERVLET MAPPINGS=====================================================================
	

	@Override
	protected String[] getServletMappings() {
		System.out.println("Get Server Mapping classes method");
		return new String[] {"/"};
	}

	
	 @Override
	    protected Filter[] getServletFilters() {
	        Filter [] singleton = { new CORSFilter() };
	        return singleton;
	    }
	  
}

	
	

