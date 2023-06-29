package org.baps.api.demo.models.base;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class PaginatedRequest {
    @Builder.Default
    private Integer pageNo = 0;
    @Builder.Default
    private Integer pageSize = 5;
}
