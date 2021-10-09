package com.proof.t2c.infrastructure.database.mysql.repositories.jpa;

import com.proof.t2c.domain.entities.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface JpaPurchaseRepository extends JpaRepository<Purchase, Integer>, JpaSpecificationExecutor<Purchase> {

}
