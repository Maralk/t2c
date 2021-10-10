package com.proof.t2c.domain.usecases.cars;

import com.proof.t2c.domain.entities.Car;
import com.proof.t2c.domain.entities.Purchase;
import com.proof.t2c.domain.usecases.UseCase;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

import javax.transaction.Transactional;

public class CreateCarUseCase implements UseCase<CreateCarUseCase.InputPort, CreateCarUseCase.OutputPort> {

    // @formatter:off
    private CarRepository carRepository;
    private PurchaseRepository purchaseRepository;
    // @formatter:on

    public CreateCarUseCase(
        CarRepository carRepository,
        PurchaseRepository purchaseRepository
    ) {
        this.carRepository = carRepository;
        this.purchaseRepository = purchaseRepository;
    }

    @Transactional
    @Override
    public OutputPort execute(InputPort inputPort) {
        Car carData = inputPort.getData();
        Car car = Car.builder()
            .carLicense(carData.getCarLicense())
            .model(carData.getModel())
            .owner(carData.getOwner())
            .build();

        Car createdCar = this.carRepository.saveCar(car);

        Purchase purchaseData = carData.getPurchase();
        Purchase purchase = Purchase.builder()
            .car(createdCar)
            .previousOwner(purchaseData.getPreviousOwner())
            .newOwner(purchaseData.getNewOwner())
            .price(purchaseData.getPrice())
            .date(purchaseData.getDate())
            .build();

        Purchase createdPurchase = this.purchaseRepository.savePurchase(purchase);
        createdCar.setPurchase(createdPurchase);

        return new OutputPort(this.carRepository.saveCar(createdCar));
    }

    // @formatter:off
    public interface CarRepository {
        Car saveCar(Car car);
    }

    public interface PurchaseRepository {
        Purchase savePurchase(Purchase purchase);
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
