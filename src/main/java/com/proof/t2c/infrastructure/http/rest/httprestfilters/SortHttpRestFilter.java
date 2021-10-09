package com.proof.t2c.infrastructure.http.rest.httprestfilters;

import com.proof.t2c.domain.filters.SortFilter;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SortHttpRestFilter {

    // @formatter:off
    private String sortBy;
    private String sortDirection;
    // @formatter:on

    public SortFilter toEntity() {
        SortFilter sortFilter = SortFilter.builder()
            .sortBy(this.sortBy)
            .sortDirection(this.sortDirection)
            .build();
        return sortFilter;
    }
}
