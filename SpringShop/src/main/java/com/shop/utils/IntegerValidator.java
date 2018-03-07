package com.shop.utils;

public class IntegerValidator {
	public static boolean isPositiveInteger(String s) {
		int test=0;
	    try { 
	        test = Integer.parseInt(s); 
	    } catch(NumberFormatException e) { 
	        return false; 
	    } catch(NullPointerException e) {
	        return false;
	    }
	    return test >0 ? true:false;
	}
}
