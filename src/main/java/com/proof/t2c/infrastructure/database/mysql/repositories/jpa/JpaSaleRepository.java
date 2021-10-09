package com.proof.t2c.infrastructure.database.mysql.repositories.jpa;

import com.proof.t2c.domain.entities.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface JpaSaleRepository extends JpaRepository<Sale, Integer>, JpaSpecificationExecutor<Sale> {

}
