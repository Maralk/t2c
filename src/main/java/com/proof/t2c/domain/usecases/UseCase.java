package com.proof.t2c.domain.usecases;

public interface UseCase<UseCaseInputPort extends UseCase.InputPort, UseCaseOutputPort extends UseCase.OutputPort> {

    UseCaseOutputPort execute(UseCaseInputPort inputPort);

    interface InputPort {
    }

    interface OutputPort {
    }

}
