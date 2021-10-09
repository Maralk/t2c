package com.proof.t2c.infrastructure.http.rest.controllers;

import com.proof.t2c.domain.interactors.UseCaseInteractor;
import com.proof.t2c.domain.usecases.sales.*;
import com.proof.t2c.infrastructure.http.rest.httprestentities.SaleHttpRestEntity;
import com.proof.t2c.infrastructure.http.rest.httprestfilters.SortHttpRestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api/sales", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.ALL_VALUE}, produces = MediaType.APPLICATION_JSON_VALUE)
public class SaleController {

    @Autowired
    protected UseCaseInteractor useCaseInteractor;

    @Autowired
    private UpdateSaleUseCase updateSaleUseCase;

    @PutMapping("/{id}")
    public CompletableFuture<SaleHttpRestEntity> update(@PathVariable Integer id, @RequestBody SaleHttpRestEntity data) {
        return this.useCaseInteractor.execute(
            this.updateSaleUseCase,
            UpdateSaleUseCase.InputPort.builder()
                .id(id)
                .data(data.toEntity())
                .build(),
            (outputPort) -> SaleHttpRestEntity.fromEntity(outputPort.getSale())
        );
    }

}
