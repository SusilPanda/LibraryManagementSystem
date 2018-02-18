package com.librarymanagement.common;

import org.junit.platform.commons.util.StringUtils;

public class LibraryManagementUtility {
	
	public static boolean validateParameters(Object param) {
		
		if(param instanceof Integer) {
			return (int)param >= 0 ;
		} else if(param instanceof Long) {
			return (long)param >= 0L;
		} else if(param instanceof String) {
			return StringUtils.isNotBlank((String) param);
		}
		return false;
		
	}

}
