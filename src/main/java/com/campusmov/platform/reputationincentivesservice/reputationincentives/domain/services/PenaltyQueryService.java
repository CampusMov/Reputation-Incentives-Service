package com.campusmov.platform.reputationincentivesservice.reputationincentives.domain.services;

import com.campusmov.platform.reputationincentivesservice.reputationincentives.domain.model.entities.Penalty;
import com.campusmov.platform.reputationincentivesservice.reputationincentives.domain.model.queries.GetAllPenaltiesByUserIdQuery;

import java.util.Collection;

public interface PenaltyQueryService {
    Collection<Penalty> handle(GetAllPenaltiesByUserIdQuery query);
}
