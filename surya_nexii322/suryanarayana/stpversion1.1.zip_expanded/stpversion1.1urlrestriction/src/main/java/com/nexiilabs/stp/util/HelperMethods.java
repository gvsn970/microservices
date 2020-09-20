package com.nexiilabs.stp.util;

public class HelperMethods {
	//private static Logger log = Logger.getLogger(HelperMethods.class);
	public static boolean checkStringEmptyOrNot(String literal){
		if(literal==null || literal.trim().equals("") ||literal.equals(null)){
			return false;
		}
			return true;
	}

}
