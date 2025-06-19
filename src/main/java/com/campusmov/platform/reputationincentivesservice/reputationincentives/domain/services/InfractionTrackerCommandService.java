package com.campusmov.platform.reputationincentivesservice.reputationincentives.domain.services;

import com.campusmov.platform.reputationincentivesservice.reputationincentives.domain.model.aggregates.InfractionTracker;
import com.campusmov.platform.reputationincentivesservice.reputationincentives.domain.model.commands.CreateInfractionTrackerCommand;

import java.util.Optional;

public interface InfractionTrackerCommandService {
    Optional<InfractionTracker> handle(CreateInfractionTrackerCommand createInfractionTrackerCommand);
    Optional<InfractionTracker> handleReset(CreateInfractionTrackerCommand createInfractionTrackerCommand);

}
