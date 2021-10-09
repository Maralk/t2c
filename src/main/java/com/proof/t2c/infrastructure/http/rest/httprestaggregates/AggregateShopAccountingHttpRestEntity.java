package com.proof.t2c.infrastructure.http.rest.httprestaggregates;

import com.proof.t2c.domain.aggregates.AggregateShopAccounting;
import com.proof.t2c.infrastructure.http.rest.httprestentities.ShopHttpRestEntity;
import lombok.*;

@Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
public class AggregateShopAccountingHttpRestEntity {

    // @formatter:off
    private ShopHttpRestEntity shop;
    private Float expenses;
    private Float incomes;
    private Float profits;
    // @formatter:on

    public static AggregateShopAccountingHttpRestEntity fromEntity(AggregateShopAccounting aggregateShopAccounting) {
        if (aggregateShopAccounting == null) {
            return null;
        }
        return AggregateShopAccountingHttpRestEntity.builder()
            .shop(ShopHttpRestEntity.fromEntity(aggregateShopAccounting.getShop()))
            .expenses(aggregateShopAccounting.getExpenses())
            .incomes(aggregateShopAccounting.getIncomes())
            .profits(aggregateShopAccounting.getProfits())
            .build();
    }

    public AggregateShopAccounting toEntity() {
        return AggregateShopAccounting.builder()
            .shop(this.shop != null ? this.shop.toEntity() : null)
            .expenses(this.expenses)
            .incomes(this.incomes)
            .profits(this.profits)
            .build();
    }

}
