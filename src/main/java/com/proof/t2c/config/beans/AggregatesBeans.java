package com.proof.t2c.config.beans;

import com.proof.t2c.domain.usecases.aggregates.GetAggregateAccountingUseCase;
import com.proof.t2c.infrastructure.database.mysql.repositories.PurchaseRepository;
import com.proof.t2c.infrastructure.database.mysql.repositories.SaleRepository;
import com.proof.t2c.infrastructure.database.mysql.repositories.ShopRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

@Configuration
public class AggregatesBeans {

    @Bean
    @Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
    public GetAggregateAccountingUseCase getAggregateAccountingUseCase(
        SaleRepository saleRepository,
        PurchaseRepository purchaseRepository,
        ShopRepository shopRepository
    ) {
        return new GetAggregateAccountingUseCase(saleRepository, purchaseRepository, shopRepository);
    }

}
