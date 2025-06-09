package com.campusmov.platform.reputationincentivesservice.reputationincentives.interfaces.rest.transform;

import com.campusmov.platform.reputationincentivesservice.reputationincentives.domain.model.commands.CreateInfractionTrackerCommand;
import com.campusmov.platform.reputationincentivesservice.reputationincentives.interfaces.rest.resources.CreateInfractionTrackerResource;

public class CreateInfractionTrackerCommandFromResourceAssembler {
    public static CreateInfractionTrackerCommand toCommandFromResource(CreateInfractionTrackerResource resource) {
        return new CreateInfractionTrackerCommand(
                resource.userId(),
                resource.InfractionType()
        );
    }
}
