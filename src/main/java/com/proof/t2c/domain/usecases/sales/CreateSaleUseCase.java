package com.proof.t2c.domain.usecases.sales;

import com.proof.t2c.domain.entities.Sale;
import com.proof.t2c.domain.usecases.UseCase;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

import javax.transaction.Transactional;

public class CreateSaleUseCase implements UseCase<CreateSaleUseCase.InputPort, CreateSaleUseCase.OutputPort> {

    // @formatter:off
    private SaleRepository saleRepository;
    // @formatter:on

    public CreateSaleUseCase(
        SaleRepository saleRepository
    ) {
        this.saleRepository = saleRepository;
    }

    @Transactional
    @Override
    public OutputPort execute(InputPort inputPort) {
        Sale sale = Sale.builder()
            .car(inputPort.getData().getCar())
            .previousOwner(inputPort.getData().getPreviousOwner())
            .newOwner(inputPort.getData().getNewOwner())
            .price(inputPort.getData().getPrice())
            .date(inputPort.getData().getDate())
            .build();

        return new OutputPort(this.saleRepository.saveSale(sale));
    }

    // @formatter:off
    public interface SaleRepository {
        Sale saveSale(Sale sale);
    }

    @Getter @Setter @Builder @Value
    public static class InputPort implements UseCase.InputPort {
        private final Sale data;
    }

    @Getter @Setter @Builder @Value
    public static class OutputPort implements UseCase.OutputPort {
        private final Sale sale;
    }
    // @formatter:on

}
