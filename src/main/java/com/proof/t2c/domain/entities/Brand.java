package com.proof.t2c.domain.entities;

import lombok.*;

import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.Set;

@Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
public class Brand {

    private Integer id;

    @NotBlank(message = "Por favor introduce un nombre")
    private String name;

    private Set<Model> models;

    private Date createdAt;

    private Date updatedAt;

}
