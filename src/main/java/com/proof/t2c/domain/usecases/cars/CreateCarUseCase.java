package com.proof.t2c.domain.usecases.cars;

import com.proof.t2c.domain.entities.Car;
import com.proof.t2c.domain.usecases.UseCase;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

import javax.transaction.Transactional;
import java.util.Date;

public class CreateCarUseCase implements UseCase<CreateCarUseCase.InputPort, CreateCarUseCase.OutputPort> {

	// @formatter:off
    private CarRepository carRepository;
	// @formatter:on

	public CreateCarUseCase(
		CarRepository carRepository
	) {
		this.carRepository = carRepository;
	}

	@Transactional @Override
	public OutputPort execute(InputPort inputPort) {
		Date now = new Date();
		Car car = Car.builder()
			.carLicense(inputPort.getData().getCarLicense())
			.brand(inputPort.getData().getBrand())
			.model(inputPort.getData().getModel())
			.owner(inputPort.getData().getOwner())
			.createdAt(now)
			.updatedAt(now)
			.build();

		return new OutputPort(this.carRepository.saveCar(car));
	}

	// @formatter:off
	public interface CarRepository {
		Car saveCar(Car car);
	}

	@Getter @Setter @Builder @Value
	public static class InputPort implements UseCase.InputPort {
		private final Car data;
	}

	@Getter @Setter @Builder @Value
	public static class OutputPort implements UseCase.OutputPort {
		private final Car car;
	}
	// @formatter:on

}
