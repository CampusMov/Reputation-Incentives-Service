package com.campusmov.platform.reputationincentivesservice.reputationincentives.interfaces.rest.transform;

import com.campusmov.platform.reputationincentivesservice.reputationincentives.domain.model.aggregates.Valoration;
import com.campusmov.platform.reputationincentivesservice.reputationincentives.interfaces.rest.resources.ValorationResource;

public class ValorationResourceFromEntityAssembler {
    public static ValorationResource toResourceFromEntity(Valoration valoration) {
        return new ValorationResource(
                valoration.getId(),
                valoration.getUserId(),
                valoration.getSenderId(),
                valoration.getReputationScore(),
                valoration.getMessage()
        );
    }
}
