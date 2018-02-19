package com.librarymanagement.common;

public class LibraryManagementException extends Exception {

	/**
	 * Added generated serial version
	 */
	private static final long serialVersionUID = 890902261243512096L;
	
	/**
	 * @param message
	 */
	public LibraryManagementException(String message) {
        super(message);
    }

	/**
	 * @param cause
	 */
	public LibraryManagementException(Throwable cause) {
        super(cause);
    }
	
	/**
	 * @param errorCode
	 * @param message
	 */
	public LibraryManagementException(int errorCode, String message) {
		super(message);
		
	}
	
}
