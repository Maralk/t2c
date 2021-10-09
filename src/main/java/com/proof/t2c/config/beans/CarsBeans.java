package com.proof.t2c.config.beans;

import com.proof.t2c.domain.usecases.cars.*;
import com.proof.t2c.infrastructure.database.mysql.repositories.CarRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

@Configuration
public class CarsBeans {

    @Bean
    @Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
    public CreateCarUseCase createCarUseCase(
        CreateCarUseCase.CarRepository carRepository
    ) {
        return new CreateCarUseCase(carRepository);
    }

    @Bean
    @Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
    public GetCarUseCase getCarUseCase(
        CarRepository carRepository
    ) {
        return new GetCarUseCase(carRepository);
    }

    @Bean
    @Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
    public DeleteCarUseCase deleteCarUseCase(
        DeleteCarUseCase.CarRepository carRepository
    ) {
        return new DeleteCarUseCase(carRepository);
    }

    @Bean
    @Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
    public UpdateCarUseCase updateCarUseCase(
        UpdateCarUseCase.CarRepository carRepository
    ) {
        return new UpdateCarUseCase(carRepository);
    }

    @Bean
    @Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
    public FindCarsUseCase findCarsUseCase(
        FindCarsUseCase.CarRepository carRepository
    ) {
        return new FindCarsUseCase(carRepository);
    }

}
