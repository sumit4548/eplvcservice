package com.eplvc.eplvcservice.customexceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ServiceException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ServiceException(String message) {
		super(message);
	}

}
