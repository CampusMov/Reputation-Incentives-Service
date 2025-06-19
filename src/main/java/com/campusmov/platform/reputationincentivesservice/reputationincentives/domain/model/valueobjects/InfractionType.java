package com.campusmov.platform.reputationincentivesservice.reputationincentives.domain.model.valueobjects;

import lombok.Getter;

@Getter
public enum InfractionType {
    LATE_CANCELLATION(1, 2),
    REJECTED_PASSENGER(3, 4),
    LATE_COMMISSION_PAYMENT(5, 10),
    SLOW_DRIVER(4, 6);

    private final Integer penaltyCount;
    private final Integer penaltyCount2;


    InfractionType(Integer penaltyCount, Integer penaltyCount2) {
        this.penaltyCount = penaltyCount;
        this.penaltyCount2 = penaltyCount2;
    }

}