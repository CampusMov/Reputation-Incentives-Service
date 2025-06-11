package com.campusmov.platform.reputationincentivesservice.reputationincentives.interfaces.rest.transform;

import com.campusmov.platform.reputationincentivesservice.reputationincentives.domain.model.commands.CreateIncentiveCommand;
import com.campusmov.platform.reputationincentivesservice.reputationincentives.interfaces.rest.resources.CreateIncentiveResource;
import com.campusmov.platform.reputationincentivesservice.reputationincentives.interfaces.rest.resources.CreateInfractionTrackerResource;

public class CreateIncentiveCommandFromResourceAssembler {
    public static CreateIncentiveCommand toCommandFromResource(CreateIncentiveResource resource) {
        return new CreateIncentiveCommand(
                resource.userId(),
                resource.rewardType()
        );
    }
}
