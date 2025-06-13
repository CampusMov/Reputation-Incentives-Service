package com.campusmov.platform.reputationincentivesservice.reputationincentives.domain.model.commands;

public record CreateValorationCommand(
        String userId,
        String senderId,
        Integer reputationScore,
        String message
) {
    public CreateValorationCommand {
        if (userId == null || userId.isBlank()) {
            throw new IllegalArgumentException("userId is required");
        }
        if (senderId == null || senderId.isBlank()) {
            throw new IllegalArgumentException("senderId is required");
        }
        if (reputationScore == null || reputationScore < 0) {
            throw new IllegalArgumentException("reputationScore must be a non-negative integer");
        }
        if (message == null || message.isBlank()) {
            throw new IllegalArgumentException("message is required");
        }
    }
}