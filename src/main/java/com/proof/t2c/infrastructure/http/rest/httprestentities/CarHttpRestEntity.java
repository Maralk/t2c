package com.proof.t2c.infrastructure.http.rest.httprestentities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.proof.t2c.domain.entities.Car;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarHttpRestEntity {

    // @formatter:off
    private Integer id;
    private String carLicense;
    private BrandHttpRestEntity brand;
    private ModelHttpRestEntity model;
    private UserHttpRestEntity owner;
    private Boolean sold;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss", timezone = "Europe/Madrid")
    private Date createdAt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss", timezone = "Europe/Madrid")
    private Date updatedAt;
    // @formatter:on

    public static CarHttpRestEntity fromEntity(Car car) {
        if (car == null) {
            return null;
        }
        return CarHttpRestEntity.builder()
            .id(car.getId())
            .carLicense(car.getCarLicense())
            .brand(BrandHttpRestEntity.fromEntity(car.getBrand()))
            .model(ModelHttpRestEntity.fromEntity(car.getModel()))
            .owner(UserHttpRestEntity.fromEntity(car.getOwner()))
            .sold(car.getSold())
            .createdAt(car.getCreatedAt())
            .updatedAt(car.getUpdatedAt())
            .build();
    }

    public Car toEntity() {
        return Car.builder()
            .id(this.id)
            .carLicense(this.carLicense)
            .brand(this.brand != null ? this.brand.toEntity() : null)
            .model(this.model != null ? this.model.toEntity() : null)
            .owner(this.owner != null ? this.owner.toEntity() : null)
            .sold(this.sold)
            .createdAt(this.createdAt)
            .updatedAt(this.updatedAt)
            .build();
    }

}
