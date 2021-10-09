package com.proof.t2c.domain.entities;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
public class Sale extends Base {

    private Integer id;

    @NotNull(message = "Por favor introduce un vehículo")
    private Car car;

    private User previousOwner;

    private User newOwner;

    private Float price;

    @NotNull(message = "Por favor introduce una fecha")
    private Date date;

    private Date createdAt;

    private Date updatedAt;

}
