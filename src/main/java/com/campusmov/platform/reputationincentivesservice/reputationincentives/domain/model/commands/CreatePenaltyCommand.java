package com.campusmov.platform.reputationincentivesservice.reputationincentives.domain.model.commands;

public record CreatePenaltyCommand(
        String userId,
        String type,
        String status,
        String description
) {
    public CreatePenaltyCommand {
        if (userId == null || userId.isBlank()) {
            throw new IllegalArgumentException("userId is required");
        }
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("description is required");
        }
        if (type == null || type.isBlank()) {
            throw new IllegalArgumentException("type is required");
        }
        if (status == null || status.isBlank()) {
            throw new IllegalArgumentException("status is required");
        }
    }
}
