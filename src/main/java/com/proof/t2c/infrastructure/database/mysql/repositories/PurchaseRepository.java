package com.proof.t2c.infrastructure.database.mysql.repositories;

import com.proof.t2c.domain.entities.Purchase;
import com.proof.t2c.domain.entities.Purchase;
import com.proof.t2c.domain.usecases.aggregates.GetAggregateAccountingUseCase;
import com.proof.t2c.domain.usecases.cars.CreateCarUseCase;
import com.proof.t2c.infrastructure.database.mysql.repositories.jpa.JpaPurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class PurchaseRepository extends BaseRepository implements
    GetAggregateAccountingUseCase.PurchaseRepository,
    CreateCarUseCase.PurchaseRepository {

    @Autowired
    private JpaPurchaseRepository jpaPurchaseRepository;

    @Override
    public Purchase savePurchase(Purchase purchase) {
        this.preSave(purchase);
        return this.jpaPurchaseRepository.save(purchase);
    }
    
    @Override
    public List<Purchase> getPurchases() {
        return this.jpaPurchaseRepository.findAll();
    }

}
