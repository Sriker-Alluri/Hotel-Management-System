package com.project.hm.customexceptions;

public class BadRequestException extends RuntimeException {

	public BadRequestException(String message)
	{
		super(message);
	}

}
