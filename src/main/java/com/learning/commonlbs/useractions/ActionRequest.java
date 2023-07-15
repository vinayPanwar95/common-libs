package com.learning.commonlbs.useractions;

import com.learning.commonlbs.useractions.security.ContextUser;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Data
@SuperBuilder(toBuilder = true)
@Accessors(fluent = true)
public class ActionRequest {
    private ContextUser contextUser;
    private String requestedBy;
    private UUID orderId;
    private Integer orderVersionNumber;
    private boolean submitForApproval;
    private DecisionAction decisionAction;
}
