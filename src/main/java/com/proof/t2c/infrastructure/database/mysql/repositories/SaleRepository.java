package com.proof.t2c.infrastructure.database.mysql.repositories;

import com.proof.t2c.domain.entities.Sale;
import com.proof.t2c.domain.usecases.sales.UpdateSaleUseCase;
import com.proof.t2c.infrastructure.database.mysql.repositories.jpa.JpaSaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public class SaleRepository extends BaseRepository implements
    UpdateSaleUseCase.SaleRepository {

    @Autowired
    private JpaSaleRepository jpaSaleRepository;

    @Override
    public Sale getSaleById(Integer id) {
        if (id == null) {
            return null;
        }
        Optional<Sale> saleOptional = this.jpaSaleRepository.findById(id);
        return saleOptional.isPresent() ? saleOptional.get() : null;
    }

    @Override
    public Sale saveSale(Sale sale) {
        this.preSave(sale);
        return this.jpaSaleRepository.save(sale);
    }

}
