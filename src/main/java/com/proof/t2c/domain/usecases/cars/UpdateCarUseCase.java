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
public class UpdateCarUseCase implements UseCase<UpdateCarUseCase.InputPort, UpdateCarUseCase.OutputPort> {

    // @formatter:off
    private CarRepository carRepository;
    // @formatter:on

    public UpdateCarUseCase(
        CarRepository carRepository
    ) {
        this.carRepository = carRepository;
    }

    @Transactional
    @Override
    public OutputPort execute(InputPort inputPort) {
        Car car = this.carRepository.getCarById(inputPort.getId());
        if (car.getSold()) {
            throw new T2cException("El vehículo no puede modificar porque ya ha sido vendido.");
        }
        car.setCarLicense(inputPort.getData().getCarLicense());
        return new OutputPort(this.carRepository.saveCar(car));
    }

    // @formatter:off
    public interface CarRepository {
        Car getCarById(Integer carId);

        Car saveCar(Car car);
    }

    @Getter @Setter @Builder @Value
    public static class InputPort implements UseCase.InputPort {
        private final Integer id;
        private final Car data;
    }

    @Getter @Setter @Builder @Value
    public static class OutputPort implements UseCase.OutputPort {
        private final Car car;
    }
    // @formatter:on

}
