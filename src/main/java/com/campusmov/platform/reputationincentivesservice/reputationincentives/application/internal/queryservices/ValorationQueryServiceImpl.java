package com.campusmov.platform.reputationincentivesservice.reputationincentives.application.internal.queryservices;

import com.campusmov.platform.reputationincentivesservice.reputationincentives.domain.model.aggregates.Valoration;
import com.campusmov.platform.reputationincentivesservice.reputationincentives.domain.model.queries.GetAllValorationsByUserIdQuery;
import com.campusmov.platform.reputationincentivesservice.reputationincentives.domain.services.ValorationQueryService;
import com.campusmov.platform.reputationincentivesservice.reputationincentives.infrastructure.persistence.jpa.repositories.ValorationRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ValorationQueryServiceImpl implements ValorationQueryService {
    private final ValorationRepository valorationRepository;

    public ValorationQueryServiceImpl(ValorationRepository valorationRepository) {
        this.valorationRepository = valorationRepository;
    }

    @Override
    public Collection<Valoration> handle(GetAllValorationsByUserIdQuery query) {
        return valorationRepository.findAllByUserId(query.userId());
    }
}
