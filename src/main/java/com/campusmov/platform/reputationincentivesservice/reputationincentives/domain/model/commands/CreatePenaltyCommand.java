package com.campusmov.platform.reputationincentivesservice.reputationincentives.domain.model.commands;

public record CreatePenaltyCommand(
        String userId,
        String type,
        String status,
        String infractionType
) {
    public CreatePenaltyCommand {
        if (userId == null || userId.isBlank()) {
            throw new IllegalArgumentException("userId is required");
        }
        if (infractionType == null || infractionType.isBlank()) {
            throw new IllegalArgumentException("infractionType is required");
        }
        if (type == null || type.isBlank()) {
            throw new IllegalArgumentException("type is required");
        }
        if (status == null || status.isBlank()) {
            throw new IllegalArgumentException("status is required");
        }
    }
}
