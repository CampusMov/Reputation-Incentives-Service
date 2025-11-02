package com.campusmov.platform.reputationincentivesservice.reputationincentives.interfaces.rest.resources;

import java.util.Date;

public record PenaltyResource(
        String id,
        String userId,
        String type,
        String status,
        String description,
        Date timestamp
) {
    public PenaltyResource {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("id is required");
        }
        if (userId == null || userId.isBlank()) {
            throw new IllegalArgumentException("userId is required");
        }
        if (type == null || type.isBlank()) {
            throw new IllegalArgumentException("type is required");
        }
        if (status == null || status.isBlank()) {
            throw new IllegalArgumentException("status is required");
        }
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("description is required");
        }
        if (timestamp == null) {
            throw new IllegalArgumentException("timestamp is required");
        }
    }
}
