package com.project.hm.customexceptions;

public class ResourceNotFoundException extends RuntimeException{
	
	public ResourceNotFoundException(String message, String string)
	{
		super(message);
	}

}
