package com.credo.ussd.payloads.wallet;

import com.credo.ussd.payloads.BaseRequest;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class FlwBaseRequest extends BaseRequest {
    private String narration;

    @JsonProperty("tx_ref")
    private String txRef;

    @JsonProperty(value = "is_permanent")
    private boolean is_permanent;

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;
}
