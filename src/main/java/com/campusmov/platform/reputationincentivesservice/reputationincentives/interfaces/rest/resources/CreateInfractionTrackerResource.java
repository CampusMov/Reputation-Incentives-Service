package com.campusmov.platform.reputationincentivesservice.reputationincentives.interfaces.rest.resources;

public record CreateInfractionTrackerResource(
        String userId,
        String InfractionType
) {
    public CreateInfractionTrackerResource {
        if (userId == null || userId.isBlank()) {
            throw new IllegalArgumentException("userId is required");
        }
        if (InfractionType == null || InfractionType.isBlank()) {
            throw new IllegalArgumentException("InfractionType is required");
        }
    }
}