package com.campusmov.platform.reputationincentivesservice.reputationincentives.domain.model.commands;

public record CreateInfractionTrackerCommand(
    String userId,
    String infractionType
) {
    public CreateInfractionTrackerCommand {
        if (userId == null || userId.isBlank()) {
            throw new IllegalArgumentException("User ID cannot be null or blank");
        }
        if (infractionType == null || infractionType.isBlank()) {
            throw new IllegalArgumentException("Infraction type cannot be null or blank");
        }

    }
}
