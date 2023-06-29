package org.baps.api.demo.models.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaginatedResponse {
    private Integer pageNo;
    private Integer pageSize;
    private Long totalCount;
    private Integer totalPages;
}
