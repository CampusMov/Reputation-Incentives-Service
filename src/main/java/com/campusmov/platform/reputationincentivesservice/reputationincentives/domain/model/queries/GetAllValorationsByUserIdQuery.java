package com.campusmov.platform.reputationincentivesservice.reputationincentives.domain.model.queries;

public record GetAllValorationsByUserIdQuery(String userId) {

    public GetAllValorationsByUserIdQuery {
        if (userId == null || userId.isBlank()) {
            throw new IllegalArgumentException("userId is required");
        }
    }
}
