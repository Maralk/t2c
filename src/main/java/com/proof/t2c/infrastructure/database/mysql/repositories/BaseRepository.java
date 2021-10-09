package com.proof.t2c.infrastructure.database.mysql.repositories;

import com.proof.t2c.domain.entities.Base;
import com.proof.t2c.domain.exceptions.T2cException;
import org.hibernate.Hibernate;
import org.hibernate.proxy.HibernateProxy;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Repository @Transactional
public class BaseRepository {

    protected void preSave(Base base) {
        this.validate(base);
        if (base.getCreatedAt() == null) {
            base.setCreatedAt(new Date());
        }
        base.setUpdatedAt(new Date());
    }

    protected void validate(Base base) {
        if (base instanceof HibernateProxy) {
            base = (Base) Hibernate.unproxy(base);
        }
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<?>> constraintViolations = new HashSet<>(validator.validate(base));
        if (constraintViolations.size() > 0) {
            throw new T2cException(constraintViolations.iterator().next().getMessage());
        }
    }

}
