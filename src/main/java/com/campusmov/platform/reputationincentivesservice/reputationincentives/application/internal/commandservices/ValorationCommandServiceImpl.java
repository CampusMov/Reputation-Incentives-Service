package com.campusmov.platform.reputationincentivesservice.reputationincentives.application.internal.commandservices;

import com.campusmov.platform.reputationincentivesservice.reputationincentives.domain.model.aggregates.Valoration;
import com.campusmov.platform.reputationincentivesservice.reputationincentives.domain.model.commands.CreateValorationCommand;
import com.campusmov.platform.reputationincentivesservice.reputationincentives.domain.services.ValorationCommandService;
import com.campusmov.platform.reputationincentivesservice.reputationincentives.infrastructure.persistence.jpa.repositories.ValorationRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ValorationCommandServiceImpl implements ValorationCommandService {
    private final ValorationRepository valorationRepository;

    public ValorationCommandServiceImpl(ValorationRepository valorationRepository) {
        this.valorationRepository = valorationRepository;
    }


    @Override
    public Optional<Valoration> handle(CreateValorationCommand createValorationCommand) {

        var valoration = new Valoration(createValorationCommand);
        valorationRepository.save(valoration);
        return Optional.of(valoration);
    }
}
