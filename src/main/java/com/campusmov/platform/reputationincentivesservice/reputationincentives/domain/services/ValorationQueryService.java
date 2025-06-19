package com.campusmov.platform.reputationincentivesservice.reputationincentives.domain.services;

import com.campusmov.platform.reputationincentivesservice.reputationincentives.domain.model.aggregates.Valoration;
import com.campusmov.platform.reputationincentivesservice.reputationincentives.domain.model.queries.GetAllValorationsByUserIdQuery;

import java.util.Collection;

public interface ValorationQueryService {
    Collection<Valoration> handle(GetAllValorationsByUserIdQuery query);
}
