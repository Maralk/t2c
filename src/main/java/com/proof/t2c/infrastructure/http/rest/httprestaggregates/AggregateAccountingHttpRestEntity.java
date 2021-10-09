package com.proof.t2c.infrastructure.http.rest.httprestaggregates;

import com.proof.t2c.domain.aggregates.AggregateAccounting;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
public class AggregateAccountingHttpRestEntity {

    // @formatter:off
    private Float expenses;
    private Float incomes;
    private Float profits;
    private List<AggregateShopAccountingHttpRestEntity> details;
    // @formatter:on

    public static AggregateAccountingHttpRestEntity fromEntity(AggregateAccounting aggregateAccounting) {
        if (aggregateAccounting == null) {
            return null;
        }
        return AggregateAccountingHttpRestEntity.builder()
            .expenses(aggregateAccounting.getExpenses())
            .incomes(aggregateAccounting.getIncomes())
            .profits(aggregateAccounting.getProfits())
            .details(aggregateAccounting.getDetails() != null ? aggregateAccounting.getDetails().stream().map(AggregateShopAccountingHttpRestEntity::fromEntity).collect(Collectors.toList()) : null)
            .build();
    }

    public AggregateAccounting toEntity() {
        return AggregateAccounting.builder()
            .expenses(this.expenses)
            .incomes(this.incomes)
            .profits(this.profits)
            .details(this.details != null ? this.details.stream().map(AggregateShopAccountingHttpRestEntity::toEntity).collect(Collectors.toList()) : null)
            .build();
    }

}
