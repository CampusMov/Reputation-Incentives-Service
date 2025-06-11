package com.campusmov.platform.reputationincentivesservice.reputationincentives.application.internal.commandservices;

import com.campusmov.platform.reputationincentivesservice.reputationincentives.domain.model.aggregates.Incentive;
import com.campusmov.platform.reputationincentivesservice.reputationincentives.domain.model.commands.CreateIncentiveCommand;
import com.campusmov.platform.reputationincentivesservice.reputationincentives.domain.services.IncentiveCommandService;
import com.campusmov.platform.reputationincentivesservice.reputationincentives.infrastructure.persistence.jpa.repositories.IncentiveRepository;
import com.campusmov.platform.reputationincentivesservice.reputationincentives.infrastructure.persistence.jpa.repositories.InfractionTrackerRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class IncentiveCommandServiceImpl implements IncentiveCommandService {
    private final IncentiveRepository incentiveRepository;


    public IncentiveCommandServiceImpl(IncentiveRepository incentiveRepository) {
        this.incentiveRepository = incentiveRepository;
    }

    @Override
    public Optional<Incentive> handle(CreateIncentiveCommand createIncentiveCommand) {
        var incentive = new Incentive(createIncentiveCommand);
        incentiveRepository.save(incentive);
        return Optional.of(incentive);
    }
}
