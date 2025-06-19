package com.campusmov.platform.reputationincentivesservice.reputationincentives.interfaces.rest.resources;

public record CreateIncentiveResource(String userId, String rewardType) {
    public CreateIncentiveResource {
        if (userId == null || userId.isBlank()) {
            throw new IllegalArgumentException("userId is required");
        }
        if (rewardType == null || rewardType.isBlank()) {
            throw new IllegalArgumentException("rewardType is required");
        }
    }
}