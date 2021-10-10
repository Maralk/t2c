package com.proof.t2c.domain.usecases.aggregates;

import com.proof.t2c.domain.aggregates.AggregateAccounting;
import com.proof.t2c.domain.aggregates.AggregateShopAccounting;
import com.proof.t2c.domain.entities.Purchase;
import com.proof.t2c.domain.entities.Sale;
import com.proof.t2c.domain.entities.Shop;
import com.proof.t2c.domain.helpers.NullSafeHelper;
import com.proof.t2c.domain.usecases.UseCase;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

public class GetAggregateAccountingUseCase implements UseCase<GetAggregateAccountingUseCase.InputPort, GetAggregateAccountingUseCase.OutputPort> {

    // @formatter:off
    private SaleRepository saleRepository;
    private PurchaseRepository purchaseRepository;
    private ShopRepository shopRepository;
    // @formatter:on

    public GetAggregateAccountingUseCase(
        SaleRepository saleRepository,
        PurchaseRepository purchaseRepository,
        ShopRepository shopRepository
    ) {
        this.saleRepository = saleRepository;
        this.purchaseRepository = purchaseRepository;
        this.shopRepository = shopRepository;
    }

    @Transactional
    @Override
    public OutputPort execute(InputPort inputPort) {
        List<Purchase> purchases = this.purchaseRepository.getPurchases();
        List<Sale> sales = this.saleRepository.getSales();

        Float expenses = purchases.stream().map(Purchase::getPrice).reduce(0f, Float::sum);
        Float incomes = sales.stream().map(Sale::getPrice).reduce(0f, Float::sum);
        Float profits = incomes - expenses;

        List<AggregateShopAccounting> details = this.calculateAggregateShopAccountings(purchases, sales);

        AggregateAccounting aggregateAccounting = AggregateAccounting.builder()
            .expenses(expenses)
            .incomes(incomes)
            .profits(profits)
            .details(details)
            .build();

        return new OutputPort(aggregateAccounting);
    }

    private List<AggregateShopAccounting> calculateAggregateShopAccountings(List<Purchase> purchases, List<Sale> sales) {
        List<Shop> shops = this.shopRepository.getShops();
        List<AggregateShopAccounting> details = new ArrayList<>();
        for (Shop shop : shops) {
            Float shopExpenses = purchases.stream().filter(purchase -> shop.getId().equals(NullSafeHelper.optional(() -> purchase.getNewOwner().getShop().getId(), null))).map(Purchase::getPrice).reduce(0f, Float::sum);
            Float shopIncomes = sales.stream().filter(sale -> shop.getId().equals(NullSafeHelper.optional(() -> sale.getPreviousOwner().getShop().getId(), null))).map(Sale::getPrice).reduce(0f, Float::sum);

            Float shopProfits = shopIncomes - shopExpenses;
            AggregateShopAccounting aggregateShopAccounting = AggregateShopAccounting.builder()
                .shop(shop)
                .expenses(shopExpenses)
                .incomes(shopIncomes)
                .profits(shopProfits)
                .build();
            details.add(aggregateShopAccounting);
        }
        return details;
    }

    public interface SaleRepository {
        List<Sale> getSales();
    }

    public interface PurchaseRepository {
        List<Purchase> getPurchases();
    }

    public interface ShopRepository {
        List<Shop> getShops();
    }

    // @formatter:off
    @Getter @Setter @Builder @Value
    public static class InputPort implements UseCase.InputPort {
    }

    @Getter @Setter @Builder @Value
    public static class OutputPort implements UseCase.OutputPort {
        private final AggregateAccounting aggregateAccounting;
    }
    // @formatter:on

}
