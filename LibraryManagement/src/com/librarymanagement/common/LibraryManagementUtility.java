package com.librarymanagement.common;

import org.h2.util.StringUtils;

public class LibraryManagementUtility {
	
	public static boolean validateParameters(Object param) {
		
		if(param instanceof Integer) {
			return (int)param >= 0 ;
		} else if(param instanceof Long) {
			return (long)param >= 0L;
		} else if(param instanceof String) {
			return StringUtils.isNullOrEmpty((String) param);
		}
		return false;
		
	}

}
