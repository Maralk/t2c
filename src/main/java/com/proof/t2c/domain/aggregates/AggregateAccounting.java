package com.proof.t2c.domain.aggregates;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter @Builder
public class AggregateAccounting {

    private String name;
    private Float expenses;
    private Float incomes;
    private Float profits;
    private List<AggregateShopAccounting> details;

}

