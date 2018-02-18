package com.librarymanagement.common;

public class LibraryManagementException extends Exception {

	/**
	 * Added generated serial version
	 */
	private static final long serialVersionUID = 890902261243512096L;
	
	public LibraryManagementException(String message) {
        super(message);
    }

	public LibraryManagementException(Throwable cause) {
        super(cause);
    }
	
	public LibraryManagementException(int errorCode, String message) {
		super(message);
		
	}
	
}
