package com.proof.t2c.infrastructure.http.rest.httprestentities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.proof.t2c.domain.entities.User;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserHttpRestEntity {

    // @formatter:off
    private Integer id;
    private String email;
    private ShopHttpRestEntity shop;
    private String name;
    private String lastName;
    private String phone;
    private String country;
    private String province;
    private String town;
    private String zip;
    private String address;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss", timezone = "Europe/Madrid")
    private Date createdAt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss", timezone = "Europe/Madrid")
    private Date updatedAt;
    // @formatter:on

    public static UserHttpRestEntity fromEntity(User user) {
        if (user == null) {
            return null;
        }
        return UserHttpRestEntity.builder()
            .id(user.getId())
            .email(user.getEmail())
            .shop(ShopHttpRestEntity.fromEntity(user.getShop()))
            .name(user.getName())
            .lastName(user.getLastName())
            .phone(user.getPhone())
            .country(user.getCountry())
            .province(user.getProvince())
            .town(user.getTown())
            .zip(user.getZip())
            .address(user.getAddress())
            .createdAt(user.getCreatedAt())
            .updatedAt(user.getUpdatedAt())
            .build();
    }

    public User toEntity() {
        return User.builder()
            .id(this.id)
            .email(this.email)
            .shop(this.shop != null ? this.shop.toEntity() : null)
            .name(this.name)
            .lastName(this.lastName)
            .phone(this.phone)
            .country(this.country)
            .province(this.province)
            .town(this.town)
            .zip(this.zip)
            .address(this.address)
            .createdAt(this.createdAt)
            .updatedAt(this.updatedAt)
            .build();
    }

}
