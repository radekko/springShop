package com.shop.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import javax.servlet.Filter;

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer{

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[]{RootConfig.class};
	}
	/* Dispatcher servlet */ 
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[]{WebConfig.class};
	}

	@Override
	protected String[] getServletMappings() {
		return new String[]{ "/" };
	}
	/* Given possibility send delete request from form to controller */ 
	@Override
	protected Filter[] getServletFilters() {
		return new Filter[] { new HiddenHttpMethodFilter()};
	 };
	

}
