package com.selecthelper.exception;

public class FactoryNotDefinedException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 112345L;

	public FactoryNotDefinedException() {
		super();
	}

	public FactoryNotDefinedException(String string, Throwable throwable, boolean b, boolean c) {
		super(string, throwable, b, c);
	}

	public FactoryNotDefinedException(String string, Throwable throwable) {
		super(string, throwable);
	}

	public FactoryNotDefinedException(String string) {
		super(string);
	}

	public FactoryNotDefinedException(Throwable throwable) {
		super(throwable);
	}
}
