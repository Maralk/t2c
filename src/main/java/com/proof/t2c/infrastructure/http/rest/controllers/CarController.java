package com.proof.t2c.infrastructure.http.rest.controllers;

import com.proof.t2c.domain.interactors.UseCaseInteractor;
import com.proof.t2c.domain.usecases.cars.*;
import com.proof.t2c.infrastructure.http.rest.httprestentities.CarHttpRestEntity;
import com.proof.t2c.infrastructure.http.rest.httprestfilters.SortHttpRestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api/cars", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.ALL_VALUE}, produces = MediaType.APPLICATION_JSON_VALUE)
public class CarController {

    @Autowired
    protected UseCaseInteractor useCaseInteractor;

    @Autowired
    private CreateCarUseCase createCarUseCase;

    @Autowired
    private GetCarUseCase getCarUseCase;

    @Autowired
    private UpdateCarUseCase updateCarUseCase;

    @Autowired
    private DeleteCarUseCase deleteCarUseCase;

    @Autowired
    private FindCarsUseCase findCarsUseCase;

    @GetMapping("/{id}")
    public CompletableFuture<CarHttpRestEntity> getById(@PathVariable Integer id) {
        return this.useCaseInteractor.execute(
            this.getCarUseCase,
            GetCarUseCase.InputPort.builder()
                .id(id)
                .build(),
            (outputPort) -> CarHttpRestEntity.fromEntity(outputPort.getCar())
        );
    }

    @PostMapping("")
    public CompletableFuture<CarHttpRestEntity> create(@RequestBody CarHttpRestEntity data) {
        return this.useCaseInteractor.execute(
            this.createCarUseCase,
            CreateCarUseCase.InputPort.builder()
                .data(data.toEntity())
                .build(),
            (outputPort) -> CarHttpRestEntity.fromEntity(outputPort.getCar())
        );
    }

    @PutMapping("/{id}")
    public CompletableFuture<CarHttpRestEntity> update(@PathVariable Integer id, @RequestBody CarHttpRestEntity data) {
        return this.useCaseInteractor.execute(
            this.updateCarUseCase,
            UpdateCarUseCase.InputPort.builder()
                .id(id)
                .data(data.toEntity())
                .build(),
            (outputPort) -> CarHttpRestEntity.fromEntity(outputPort.getCar())
        );
    }

    @DeleteMapping("/{id}")
    public CompletableFuture<ResponseEntity> delete(@PathVariable Integer id) {
        return this.useCaseInteractor.execute(
            this.deleteCarUseCase,
            DeleteCarUseCase.InputPort.builder()
                .id(id)
                .build(),
            (outputPort) -> null
        );
    }

    @PostMapping("/findByFilter")
    public CompletableFuture<List<CarHttpRestEntity>> findByFilter(@RequestBody SortHttpRestFilter filter) {
        return this.useCaseInteractor.execute(
            this.findCarsUseCase,
            FindCarsUseCase.InputPort.builder()
                .filter(filter.toEntity())
                .build(),
            (outputPort) ->
                outputPort.getCars().stream().map(CarHttpRestEntity::fromEntity).collect(Collectors.toList())
        );
    }

}
