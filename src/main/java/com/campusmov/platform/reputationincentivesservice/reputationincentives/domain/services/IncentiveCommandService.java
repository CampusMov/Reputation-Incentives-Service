package com.campusmov.platform.reputationincentivesservice.reputationincentives.domain.services;

import com.campusmov.platform.reputationincentivesservice.reputationincentives.domain.model.aggregates.Incentive;
import com.campusmov.platform.reputationincentivesservice.reputationincentives.domain.model.commands.CreateIncentiveCommand;

import java.util.Optional;

public interface IncentiveCommandService {
    Optional<Incentive> handle(CreateIncentiveCommand createIncentiveCommand);
}
