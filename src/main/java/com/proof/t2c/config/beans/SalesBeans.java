package com.proof.t2c.config.beans;

import com.proof.t2c.domain.usecases.sales.CreateSaleUseCase;
import com.proof.t2c.domain.usecases.sales.UpdateSaleUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

@Configuration
public class SalesBeans {

    @Bean
    @Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
    public CreateSaleUseCase createSaleUseCase(
        CreateSaleUseCase.SaleRepository saleRepository) {
        return new CreateSaleUseCase(saleRepository);
    }

    @Bean
    @Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
    public UpdateSaleUseCase updateSaleUseCase(
        UpdateSaleUseCase.SaleRepository saleRepository
    ) {
        return new UpdateSaleUseCase(saleRepository);
    }

}
