package com.proof.t2c.domain.interactors;

import com.proof.t2c.domain.usecases.UseCase;

import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

public interface UseCaseInteractor {

    <RX, InputPort extends UseCase.InputPort, OutputPort extends UseCase.OutputPort> CompletableFuture<RX> execute(UseCase<InputPort, OutputPort> useCase, InputPort input, Function<OutputPort, RX> presenter);

}

