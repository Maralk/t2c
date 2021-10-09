package com.proof.t2c.infrastructure.database.mysql.repositories;

import com.proof.t2c.domain.entities.Shop;
import com.proof.t2c.domain.usecases.aggregates.GetAggregateAccountingUseCase;
import com.proof.t2c.infrastructure.database.mysql.repositories.jpa.JpaShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class ShopRepository extends BaseRepository implements
    GetAggregateAccountingUseCase.ShopRepository {

    @Autowired
    private JpaShopRepository jpaShopRepository;

    @Override
    public List<Shop> getShops() {
        return this.jpaShopRepository.findAll();
    }

}
