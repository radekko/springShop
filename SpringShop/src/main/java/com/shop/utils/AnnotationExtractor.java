package com.shop.utils;

import org.springframework.web.bind.annotation.RequestMapping;

public class AnnotationExtractor {

	public static <T> String extractPathToController(Class<T> c) {
		RequestMapping req = c.getAnnotation(RequestMapping.class);
		
		if(req == null)
			return "";
		
		String pathToController = req.value()[0];
		return pathToController.substring(1);
	}
}
