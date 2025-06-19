package com.campusmov.platform.reputationincentivesservice.reputationincentives.domain.model.commands;

public record CreateIncentiveCommand(String userId, String rewardType) {
    public CreateIncentiveCommand {
        if (userId == null || userId.isBlank()) {
            throw new IllegalArgumentException("User ID cannot be null or blank");
        }
        if (rewardType == null || rewardType.isBlank()) {
            throw new IllegalArgumentException("Reward Type cannot be null or blank");
        }
    }
}
