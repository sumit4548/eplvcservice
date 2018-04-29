package com.eplvc.eplvcservice.customexceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class SessionExpiredException extends Exception{


	private static final long serialVersionUID = 1L;

	public SessionExpiredException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
}
