package com.proof.t2c.infrastructure.http.rest.httprestentities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.proof.t2c.domain.entities.Purchase;
import com.proof.t2c.infrastructure.http.rest.httprestentities.reduced.ReducedCarHttpRestEntity;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseHttpRestEntity {

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

    public static PurchaseHttpRestEntity fromEntity(Purchase purchase) {
        if (purchase == null) {
            return null;
        }
        return PurchaseHttpRestEntity.builder()
            .id(purchase.getId())
            .car(ReducedCarHttpRestEntity.fromEntity(purchase.getCar()))
            .previousOwner(UserHttpRestEntity.fromEntity(purchase.getPreviousOwner()))
            .newOwner(UserHttpRestEntity.fromEntity(purchase.getNewOwner()))
            .price(purchase.getPrice())
            .date(purchase.getDate())
            .createdAt(purchase.getCreatedAt())
            .updatedAt(purchase.getUpdatedAt())
            .build();
    }

    public Purchase toEntity() {
        return Purchase.builder()
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
