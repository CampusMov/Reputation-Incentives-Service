package com.campusmov.platform.reputationincentivesservice.reputationincentives.domain.model.commands;

public record CreateValorationCommand(
        String userId,
        String senderId,
        String reputationScore,
        String message
) {
    public CreateValorationCommand {
        if (userId == null || userId.isBlank()) {
            throw new IllegalArgumentException("userId is required");
        }
        if (senderId == null || senderId.isBlank()) {
            throw new IllegalArgumentException("senderId is required");
        }
        if (reputationScore == null || reputationScore.isBlank()) {
            throw new IllegalArgumentException("reputationScore is required");
        }
        if (message == null || message.isBlank()) {
            throw new IllegalArgumentException("message is required");
        }
    }
}