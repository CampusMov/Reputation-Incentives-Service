package com.campusmov.platform.reputationincentivesservice.reputationincentives.application.internal.queryservices;

import com.campusmov.platform.reputationincentivesservice.reputationincentives.domain.model.entities.Penalty;
import com.campusmov.platform.reputationincentivesservice.reputationincentives.domain.model.queries.GetAllPenaltiesByUserIdQuery;
import com.campusmov.platform.reputationincentivesservice.reputationincentives.domain.services.PenaltyQueryService;
import com.campusmov.platform.reputationincentivesservice.reputationincentives.infrastructure.persistence.jpa.repositories.PenaltyRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class PenaltyQueryServiceImpl implements PenaltyQueryService {
    private final PenaltyRepository penaltyRepository;

    public PenaltyQueryServiceImpl(PenaltyRepository penaltyRepository) {
        this.penaltyRepository = penaltyRepository;
    }

    @Override
    public Collection<Penalty> handle(GetAllPenaltiesByUserIdQuery query) {

        return penaltyRepository.findAllByUserId(query.userId());

    }
}
