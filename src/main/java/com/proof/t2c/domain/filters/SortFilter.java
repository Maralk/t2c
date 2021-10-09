package com.proof.t2c.domain.filters;

import lombok.*;

@Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
public class SortFilter {

    private String sortBy;
    private String sortDirection;

}
