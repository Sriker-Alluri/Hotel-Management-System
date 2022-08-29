package com.project.hm.controllerAdvice;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.project.hm.customexceptions.BadRequestException;
import com.project.hm.customexceptions.ResourceNotFoundException;
import com.project.hm.customexceptions.UserNotValidException;

@RestControllerAdvice
public class ExceptionHandlerr {
	
	@ExceptionHandler(UserNotValidException.class)
	   @ResponseStatus(value=HttpStatus.NOT_FOUND)
	public Map<String, String>handleException(UserNotValidException exception){
		HashMap<String, String> map=new HashMap<>();
		map.put("ErrorMessage", exception.getMessage());
		
		return map;
	}
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String>handleException(MethodArgumentNotValidException exception){
		HashMap<String, String> map=new HashMap<>();
		exception.getBindingResult().getFieldErrors().forEach(error->{
		map.put(error.getField(), error.getDefaultMessage());
		});
		
		return map;
	}
	@ExceptionHandler(ResourceNotFoundException.class)
	public Map<String, String>handleException(ResourceNotFoundException exception){
		HashMap<String, String> map=new HashMap<>();
		map.put("ErrorMessage", exception.getMessage());
		
		return map;
	}
	@ExceptionHandler(BadRequestException.class)
	public Map<String, String>handleException(BadRequestException exception){
		HashMap<String, String> map=new HashMap<>();
		map.put("ErrorMessage", exception.getMessage());
		
		return map;
	}
}
