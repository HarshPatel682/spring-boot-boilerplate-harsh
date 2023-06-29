package org.baps.api.demo.models.responses;

import org.baps.api.demo.models.error.Error;

import lombok.Builder;
import lombok.Data;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

@Data
@Builder
@SuppressFBWarnings({"EI_EXPOSE_REP", "EI_EXPOSE_REP2"})
public class ApiErrorResponse {

    private Error error;

}
