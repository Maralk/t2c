package com.proof.t2c.infrastructure.http.rest.httprestentities.reduced;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.proof.t2c.domain.entities.Car;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReducedCarHttpRestEntity {

    // @formatter:off
    private Integer id;
    private String carLicense;
    private Boolean sold;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss", timezone = "Europe/Madrid")
    private Date createdAt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss", timezone = "Europe/Madrid")
    private Date updatedAt;
    // @formatter:on

    public static ReducedCarHttpRestEntity fromEntity(Car car) {
        if (car == null) {
            return null;
        }
        return ReducedCarHttpRestEntity.builder()
            .id(car.getId())
            .carLicense(car.getCarLicense())
            .sold(car.getSold())
            .createdAt(car.getCreatedAt())
            .updatedAt(car.getUpdatedAt())
            .build();
    }

    public Car toEntity() {
        return Car.builder()
            .id(this.id)
            .carLicense(this.carLicense)
            .sold(this.sold)
            .createdAt(this.createdAt)
            .updatedAt(this.updatedAt)
            .build();
    }

}
