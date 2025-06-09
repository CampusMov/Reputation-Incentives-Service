package com.campusmov.platform.reputationincentivesservice.reputationincentives.interfaces.rest.transform;

import com.campusmov.platform.reputationincentivesservice.reputationincentives.domain.model.entities.Penalty;
import com.campusmov.platform.reputationincentivesservice.reputationincentives.interfaces.rest.resources.PenaltyResource;

public class PenaltyResourceFromEntityAssembler {
    public static PenaltyResource toResourceFromEntity(Penalty penalty) {
        return new PenaltyResource(
                penalty.getId(),
                penalty.getUserId(),
                penalty.getType().name(),
                penalty.getStatus().name(),
                penalty.getDescription()
        );
    }
}
