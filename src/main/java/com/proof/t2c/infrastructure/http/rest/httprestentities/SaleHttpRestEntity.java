package com.proof.t2c.infrastructure.http.rest.httprestentities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.proof.t2c.domain.entities.Model;
import com.proof.t2c.domain.entities.Sale;
import com.proof.t2c.infrastructure.http.rest.httprestentities.reduced.ReducedCarHttpRestEntity;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SaleHttpRestEntity {

    // @formatter:off
    private Integer id;
    private ReducedCarHttpRestEntity car;
    private UserHttpRestEntity previousOwner;
    private UserHttpRestEntity newOwner;
    private Float price;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss", timezone = "Europe/Madrid")
    private Date date;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss", timezone = "Europe/Madrid")
    private Date createdAt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss", timezone = "Europe/Madrid")
    private Date updatedAt;
    // @formatter:on

    public static SaleHttpRestEntity fromEntity(Sale sale) {
        if (sale == null) {
            return null;
        }
        return SaleHttpRestEntity.builder()
            .id(sale.getId())
            .car(ReducedCarHttpRestEntity.fromEntity(sale.getCar()))
            .previousOwner(UserHttpRestEntity.fromEntity(sale.getPreviousOwner()))
            .newOwner(UserHttpRestEntity.fromEntity(sale.getNewOwner()))
            .price(sale.getPrice())
            .date(sale.getDate())
            .createdAt(sale.getCreatedAt())
            .updatedAt(sale.getUpdatedAt())
            .build();
    }

    public Sale toEntity() {
        return Sale.builder()
            .id(this.id)
            .car(this.car != null ? this.car.toEntity() : null)
            .previousOwner(this.previousOwner != null ? this.previousOwner.toEntity() : null)
            .newOwner(this.newOwner != null ? this.newOwner.toEntity() : null)
            .price(this.price)
            .date(this.date)
            .createdAt(this.createdAt)
            .updatedAt(this.updatedAt)
            .build();
    }

}
