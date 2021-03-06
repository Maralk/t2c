package com.proof.t2c.infrastructure.http.rest.httprestentities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.proof.t2c.domain.entities.Brand;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BrandHttpRestEntity {

    // @formatter:off
    private Integer id;
    private String name;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss", timezone = "Europe/Madrid")
    private Date createdAt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss", timezone = "Europe/Madrid")
    private Date updatedAt;
    // @formatter:on

    public static BrandHttpRestEntity fromEntity(Brand brand) {
        if (brand == null) {
            return null;
        }
        return BrandHttpRestEntity.builder()
            .id(brand.getId())
            .name(brand.getName())
            .createdAt(brand.getCreatedAt())
            .updatedAt(brand.getUpdatedAt())
            .build();
    }

    public Brand toEntity() {
        return Brand.builder()
            .id(this.id)
            .name(this.name)
            .createdAt(this.createdAt)
            .updatedAt(this.updatedAt)
            .build();
    }

}
