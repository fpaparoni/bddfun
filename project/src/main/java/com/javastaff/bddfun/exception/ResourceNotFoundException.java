package com.javastaff.bddfun.exception;

/**
 * Exception for resource not found (404)
 */
public class ResourceNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7238423346243937296L;

	public ResourceNotFoundException(String message) {
		super(message);
	}

}
