package com.rjxy.exception;

//�Զ����쳣
public class ShoppingException extends RuntimeException{

	public ShoppingException() {
		super();
	}

	public ShoppingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ShoppingException(String message, Throwable cause) {
		super(message, cause);
	}

	public ShoppingException(String message) {
		super(message);
	}

	public ShoppingException(Throwable cause) {
		super(cause);
	}

	
}
