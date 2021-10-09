package com.proof.t2c.domain.interactors.impl;

import com.proof.t2c.domain.interactors.UseCaseInteractor;
import com.proof.t2c.domain.usecases.UseCase;

import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

public class UseCaseInteractorImpl implements UseCaseInteractor {

    public <RX, InputPort extends UseCase.InputPort, OutputPort extends UseCase.OutputPort> CompletableFuture<RX> execute(UseCase<InputPort, OutputPort> useCase, InputPort inputPort, Function<OutputPort, RX> presenter) {
        return CompletableFuture
            .supplyAsync(() -> inputPort)
            .thenApplyAsync(useCase::execute)
            .thenApplyAsync(presenter);
    }

}
