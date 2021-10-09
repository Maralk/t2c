package com.proof.t2c.infrastructure.http.rest.httprestentities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.proof.t2c.domain.entities.Model;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ModelHttpRestEntity {

    // @formatter:off
    private Integer id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss", timezone = "Europe/Madrid")
    private Date createdAt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss", timezone = "Europe/Madrid")
    private Date updatedAt;
    // @formatter:on

    public static ModelHttpRestEntity fromEntity(Model model) {
        if (model == null) {
            return null;
        }
        return ModelHttpRestEntity.builder()
            .id(model.getId())
            .createdAt(model.getCreatedAt())
            .updatedAt(model.getUpdatedAt())
            .build();
    }

    public Model toEntity() {
        return Model.builder()
            .id(this.id)
            .createdAt(this.createdAt)
            .updatedAt(this.updatedAt)
            .build();
    }

}
