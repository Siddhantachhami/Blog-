package com.siddhant.blog.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter 
public class ResourceNotFoundException extends RuntimeException{
	String resourceName;
	String fieldName;
	long fieldValue;
	String fieldUsername;
	public ResourceNotFoundException(String resourceName, String fieldName, long fieldValue) {
		super(String.format("%s not found with %s: %s", resourceName,fieldName,fieldValue));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}
	public ResourceNotFoundException(String resourceName, String fieldName, String fieldUsername) {
		super(String.format("%s not found with %s: %s", resourceName,fieldName,fieldUsername));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldUsername = fieldUsername;
	}
	
	
	
}


