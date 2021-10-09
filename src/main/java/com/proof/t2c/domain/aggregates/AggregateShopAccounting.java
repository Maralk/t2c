package com.proof.t2c.domain.aggregates;

import com.proof.t2c.domain.entities.Shop;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter @Builder
public class AggregateShopAccounting {

    private Shop shop;
    private Float expenses;
    private Float incomes;
    private Float profits;

}

