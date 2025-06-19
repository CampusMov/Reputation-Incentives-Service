package com.campusmov.platform.reputationincentivesservice.reputationincentives.domain.services;

import com.campusmov.platform.reputationincentivesservice.reputationincentives.domain.model.aggregates.Valoration;
import com.campusmov.platform.reputationincentivesservice.reputationincentives.domain.model.commands.CreateValorationCommand;

import java.util.Optional;

public interface ValorationCommandService {
    Optional<Valoration> handle(CreateValorationCommand createValorationCommand);
}
