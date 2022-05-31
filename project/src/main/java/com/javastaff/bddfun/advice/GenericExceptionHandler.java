package com.javastaff.bddfun.advice;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.javastaff.bddfun.exception.ResourceNotFoundException;
/**
 * 
 * Generic Exception Handler class
 *
 */
@RestControllerAdvice
public class GenericExceptionHandler {
	
	
	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorDTO handleConstraintViolationException(ConstraintViolationException e) {
		var errorDTO =  new ErrorDTO();
		List<String> messageList=new ArrayList<>();
		messageList.add("ConstraintViolationException :"+e.getMessage());
		errorDTO.setErrorList(messageList);
		return errorDTO;
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ErrorDTO handleResourceNotFoundException(ResourceNotFoundException ex) {
		var errorDTO = new ErrorDTO();
		List<String> messageList=new ArrayList<>();
		var applicationMessage="Resource not found: " + ex.getMessage();
		messageList.add(applicationMessage);
		errorDTO.setErrorList(messageList);
	    return errorDTO;
	}
	
	/**
	 * {@link MethodArgumentNotValidException} handler
	 * 
	 * @param ex
	 * @return
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ErrorDTO handleValidationExceptions(
	  MethodArgumentNotValidException ex) {
		var errorDTO = new ErrorDTO();
		List<String> messageList=new ArrayList<>();
		ex.getBindingResult().getAllErrors().forEach(error -> {
	        String fieldName = ((FieldError) error).getField();
	        String code = ((FieldError) error).getCode();
	        String errorMessage = error.getDefaultMessage();
	        var message=new StringBuffer()
	        		.append(errorMessage)
	        		.append(" ")
	        		.append(fieldName)
	        		.append(" ")
	        		.append(code).toString();
	        messageList.add(message);
	    });
		errorDTO.setErrorList(messageList);
	    return errorDTO;
	}
	
}
