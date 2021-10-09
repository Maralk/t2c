package com.proof.t2c.domain.entities;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
public class Model extends Base {

    private Integer id;

    @NotBlank(message = "Por favor introduce un nombre")
    private String name;

    @NotNull(message = "Por favor introduce una marca")
    private Brand brand;

    private Date createdAt;

    private Date updatedAt;

}
