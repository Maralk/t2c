package com.proof.t2c.domain.usecases.cars;

import com.proof.t2c.domain.entities.Car;
import com.proof.t2c.domain.exceptions.T2cException;
import com.proof.t2c.domain.usecases.UseCase;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

import javax.transaction.Transactional;

@Getter @Setter @Builder
public class DeleteCarUseCase implements UseCase<DeleteCarUseCase.InputPort, DeleteCarUseCase.OutputPort> {

    // @formatter:off
    private CarRepository carRepository;
    // @formatter:on

    public DeleteCarUseCase(
        CarRepository carRepository
    ) {
        this.carRepository = carRepository;
    }

    @Transactional
    @Override
    public OutputPort execute(InputPort inputPort) {
        Car car = this.carRepository.getCarById(inputPort.getId());
        if (car.getIsSold()) {
            throw new T2cException("El vehículo no puede dar de baja porque ya ha sido vendido.");
        }
        this.carRepository.deleteCar(car);
        return new OutputPort();
    }

    // @formatter:off
    public interface CarRepository {
        Car getCarById(Integer carId);

        void deleteCar(Car car);
    }

    @Getter @Setter @Builder @Value
    public static class InputPort implements UseCase.InputPort {
        private final Integer id;
    }

    @Getter @Setter @Builder @Value
    public static class OutputPort implements UseCase.OutputPort {
    }
    // @formatter:on

}
