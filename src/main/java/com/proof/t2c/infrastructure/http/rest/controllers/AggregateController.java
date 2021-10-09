package com.proof.t2c.infrastructure.http.rest.controllers;

import com.proof.t2c.domain.interactors.UseCaseInteractor;
import com.proof.t2c.domain.usecases.aggregates.GetAggregateAccountingUseCase;
import com.proof.t2c.infrastructure.http.rest.httprestaggregates.AggregateAccountingHttpRestEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(path = "/api/aggregate-accounting", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.ALL_VALUE}, produces = MediaType.APPLICATION_JSON_VALUE)
public class AggregateController {

    @Autowired
    protected UseCaseInteractor useCaseInteractor;

    @Autowired
    private GetAggregateAccountingUseCase getAggregateAccountingUseCase;


    @PostMapping("")
    public CompletableFuture<AggregateAccountingHttpRestEntity> findByFilter() {
        return this.useCaseInteractor.execute(
            this.getAggregateAccountingUseCase,
            GetAggregateAccountingUseCase.InputPort.builder()
                .build(),
            (outputPort) ->
                AggregateAccountingHttpRestEntity.fromEntity(outputPort.getAggregateAccounting())
        );
    }

}
