package com.proof.t2c.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackages = {"com.proof.t2c.domain.entities"})
@EnableJpaRepositories(basePackages = {"com.proof.t2c.infrastructure.database"})
@ComponentScan("com.proof.t2c.infrastructure.database")
public class ApplicationDatabase {
}
