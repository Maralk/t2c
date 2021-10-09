package com.proof.t2c.domain.usecases.sales;

import com.proof.t2c.domain.entities.Sale;
import com.proof.t2c.domain.usecases.UseCase;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

import javax.transaction.Transactional;

@Getter @Setter @Builder
public class UpdateSaleUseCase implements UseCase<UpdateSaleUseCase.InputPort, UpdateSaleUseCase.OutputPort> {

    // @formatter:off
    private SaleRepository SaleRepository;
    // @formatter:on

    public UpdateSaleUseCase(
        SaleRepository SaleRepository
    ) {
        this.SaleRepository = SaleRepository;
    }

    @Transactional
    @Override
    public OutputPort execute(InputPort inputPort) {
        Sale sale = this.SaleRepository.getSaleById(inputPort.getId());
        sale.setDate(inputPort.data.getDate());
        sale.setPrice(inputPort.data.getPrice());
        return new OutputPort(this.SaleRepository.saveSale(sale));
    }

    // @formatter:off
    public interface SaleRepository {
        Sale getSaleById(Integer saleId);

        Sale saveSale(Sale sale);
    }

    @Getter @Setter @Builder @Value
    public static class InputPort implements UseCase.InputPort {
        private final Integer id;
        private final Sale data;
    }

    @Getter @Setter @Builder @Value
    public static class OutputPort implements UseCase.OutputPort {
        private final Sale sale;
    }
    // @formatter:on

}
