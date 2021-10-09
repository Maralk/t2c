package com.proof.t2c.domain.usecases.cars;

import com.proof.t2c.domain.entities.Car;
import com.proof.t2c.domain.filters.SortFilter;
import com.proof.t2c.domain.usecases.UseCase;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

import javax.transaction.Transactional;
import java.util.List;

public class FindCarsUseCase implements UseCase<FindCarsUseCase.InputPort, FindCarsUseCase.OutputPort> {

    // @formatter:off
    private CarRepository carRepository;
    // @formatter:on

    public FindCarsUseCase(
        CarRepository carRepository
    ) {
        this.carRepository = carRepository;
    }

    @Transactional
    @Override
    public OutputPort execute(InputPort inputPort) {
        return new OutputPort(this.carRepository.findCars(inputPort.getFilter()));
    }

    // @formatter:off
    public interface CarRepository {
        List<Car> findCars(SortFilter sortFilter);
    }

    @Getter @Setter @Builder @Value
    public static class InputPort implements UseCase.InputPort {
        private final SortFilter filter;
    }

    @Getter @Setter @Builder @Value
    public static class OutputPort implements UseCase.OutputPort {
        private final List<Car> cars;
    }
    // @formatter:on

}
