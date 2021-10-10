package com.proof.t2c.domain.entities;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
public class Car extends Base {

    private Integer id;

    private String carLicense;

    @NotNull(message = "Por favor introduce un modelo")
    private Model model;

    @NotNull(message = "Por favor introduce un dueño")
    private User owner;

    private Purchase purchase;

    private Sale sale;

    private Boolean sold;

    private Date createdAt;

    private Date updatedAt;

    public Boolean getSold() {
        return this.sale != null;
    }
}
