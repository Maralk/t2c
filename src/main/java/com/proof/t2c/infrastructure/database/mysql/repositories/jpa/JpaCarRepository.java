package com.proof.t2c.infrastructure.database.mysql.repositories.jpa;

import com.proof.t2c.domain.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface JpaCarRepository extends JpaRepository<Car, Integer>, JpaSpecificationExecutor<Car> {

}
