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
    private String password;
    private ShopHttpRestEntity shop;
    private String name;
    private String lastName;
    private String idCard;
    private String gender;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone = "Europe/Madrid")
    private Date birthdate;
    private String phone;
    private String country;
    private String province;
    private String town;
    private Integer zip;
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
            .password(null)
            .shop(ShopHttpRestEntity.fromEntity(user.getShop()))
            .createdAt(user.getCreatedAt())
            .updatedAt(user.getUpdatedAt())
            .build();
    }

    public User toEntity() {
        return User.builder()
            .id(this.id)
            .email(this.email)
            .createdAt(this.createdAt)
            .updatedAt(this.updatedAt)
            .build();
    }

}
