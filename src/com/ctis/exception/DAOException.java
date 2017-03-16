package com.ctis.exception;

import org.springframework.core.NestedRuntimeException;

public class DAOException extends NestedRuntimeException {
	private static final long serialVersionUID = 47L;

	public DAOException(String msg) {
		super(msg);
	}

	public DAOException(Throwable ex) {
		super("", ex);
	}

	public DAOException(String msg, Throwable ex) {
		super(msg, ex);
	}
}