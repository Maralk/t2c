package com.proof.t2c.domain.usecases.cars;

import com.proof.t2c.domain.entities.Car;
import com.proof.t2c.domain.usecases.UseCase;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

import javax.transaction.Transactional;

public class GetCarUseCase implements UseCase<GetCarUseCase.InputPort, GetCarUseCase.OutputPort> {

    // @formatter:off
    private CarRepository carRepository;
    // @formatter:on

    public GetCarUseCase(
        CarRepository carRepository
    ) {
        this.carRepository = carRepository;
    }

    @Transactional
    @Override
    public OutputPort execute(InputPort inputPort) {
        if (inputPort.getId() != null) {
            return new OutputPort(this.carRepository.getCarById(inputPort.getId()));
        }
        return new OutputPort(null);
    }

    public interface CarRepository {
        Car getCarById(Integer carId);
    }

    // @formatter:off
    @Getter @Setter @Builder @Value
    public static class InputPort implements UseCase.InputPort {
        private final Integer id;
    }

    @Getter @Setter @Builder @Value
    public static class OutputPort implements UseCase.OutputPort {
        private final Car car;
    }
    // @formatter:on

}
