package com.proof.t2c.config.exceptions;

import lombok.Getter;
import lombok.Setter;

import javax.validation.ConstraintViolation;
import java.util.Set;

@Getter @Setter
public class ExceptionEntity {

    private String error;

    public ExceptionEntity(String error) {
        this.error = error;
    }

    public ExceptionEntity(Set<ConstraintViolation<?>> constraintViolations) {
        ConstraintViolation constraintViolation = constraintViolations.iterator().next();
        this.error = constraintViolation.getMessage();
    }

}
