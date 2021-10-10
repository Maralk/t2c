package com.proof.t2c.domain.entities;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
public class User extends Base {

    private Integer id;

    @NotNull(message = "Por favor introduce un email")
    private String email;

    private Shop shop;

    private String name;

    private String lastName;

    private String phone;

    private String country;

    private String province;

    private String town;

    private String zip;

    private String address;

    private Date createdAt;

    private Date updatedAt;

    private Date removedAt;


}
