package com.project.ifood.domain.service.exception;

public class NotFoundExceptionService extends RuntimeException{
	private static final long serialVersionUID = -3957675191244147274L;
	
	public NotFoundExceptionService(String message) {
		super(message);
	}

}
