package com.proof.t2c.domain.entities;

import lombok.*;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
public class Shop extends Base {

    private Integer id;

    @NotBlank(message = "Por favor introduce un nombre")
    private String name;

    private String phone;

    private String country;

    private String province;

    private String town;

    private String zip;

    private String address;

    private Date createdAt;

    private Date updatedAt;

}
