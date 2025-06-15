package com.campusmov.platform.reputationincentivesservice.reputationincentives.interfaces.rest.resources;

public record CreateValorationResource(
        String userId,
        String senderId,
        Double reputationScore,
        String message
) {
    public CreateValorationResource {
        if (userId == null || userId.isBlank()) {
            throw new IllegalArgumentException("userId is required");
        }
        if (senderId == null || senderId.isBlank()) {
            throw new IllegalArgumentException("senderId is required");
        }
        if (reputationScore == null || reputationScore < 0.0 || reputationScore > 5.0) {
            throw new IllegalArgumentException("reputationScore must be between 0.0 and 5.0");
        }
        if (message == null || message.isBlank()) {
            throw new IllegalArgumentException("message is required");
        }
    }
}
