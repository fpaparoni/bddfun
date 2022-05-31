package com.javastaff.bddfun.advice;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * DTO class for error response
 */
@Getter
@Setter
@ToString
public class ErrorDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7073321719644966068L;
	private List<String> errorList;
}

