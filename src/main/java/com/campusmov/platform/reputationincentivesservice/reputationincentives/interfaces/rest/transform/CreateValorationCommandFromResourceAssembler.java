package com.campusmov.platform.reputationincentivesservice.reputationincentives.interfaces.rest.transform;

import com.campusmov.platform.reputationincentivesservice.reputationincentives.domain.model.commands.CreateValorationCommand;
import com.campusmov.platform.reputationincentivesservice.reputationincentives.interfaces.rest.resources.CreateValorationResource;

public class CreateValorationCommandFromResourceAssembler {
    public static CreateValorationCommand toCommandFromResource(CreateValorationResource resource) {
        return new CreateValorationCommand(
                resource.userId(),
                resource.senderId(),
                resource.reputationScore(),
                resource.message()
        );
    }
}
