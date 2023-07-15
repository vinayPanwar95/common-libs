package com.learning.commonlbs.model;

import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public enum OrderStatusCode {
    DRAFT,
    REQUESTED,
    ACTIVATED,
    REQUEST_FOR_CLOSURE,
    REPLACED,
    CANCELLED,
    DECLINED,
    CLOSED;

    public static Optional<OrderStatusCode> fromString(String status){
        return  Arrays.stream(values()).filter(domainStatus->domainStatus.name().equals(status)).findAny();
    }

    public static String joinWithOrDelimiter(Set<OrderStatusCode> statues){
        return statues.stream().map(Enum :: name).collect(Collectors.joining(" or "));
    }
}
