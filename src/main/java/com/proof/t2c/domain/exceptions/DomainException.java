package com.proof.t2c.domain.exceptions;

public abstract class DomainException extends RuntimeException {

	public DomainException(String message) {
		super(message);
	}

}