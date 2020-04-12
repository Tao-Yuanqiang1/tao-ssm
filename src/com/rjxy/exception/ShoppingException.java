package com.rjxy.exception;

//自定义异常
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
