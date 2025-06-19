package com.campusmov.platform.reputationincentivesservice.reputationincentives.interfaces.rest.resources;

import java.util.Date;

public record ValorationResource(
        String id,
        String userId,
        String senderId,
        Double reputationScore,
        String message,
        Date timestamp
) {
    public ValorationResource {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("id is required");
        }
        if (userId == null || userId.isBlank()) {
            throw new IllegalArgumentException("userId is required");
        }
        if (senderId == null || senderId.isBlank()) {
            throw new IllegalArgumentException("senderId is required");
        }
        if (reputationScore == null || reputationScore < 0.0) {
            throw new IllegalArgumentException("reputationScore must be a non-negative number");
        }

        if (message == null || message.isBlank()) {
            throw new IllegalArgumentException("message is required");
        }
        if (timestamp == null) {
            throw new IllegalArgumentException("timestamp is required");
        }
    }
}
