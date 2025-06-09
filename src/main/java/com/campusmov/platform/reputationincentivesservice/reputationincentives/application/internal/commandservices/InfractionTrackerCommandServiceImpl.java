package com.campusmov.platform.reputationincentivesservice.reputationincentives.application.internal.commandservices;

import com.campusmov.platform.reputationincentivesservice.reputationincentives.domain.model.aggregates.InfractionTracker;
import com.campusmov.platform.reputationincentivesservice.reputationincentives.domain.model.commands.CreateInfractionTrackerCommand;
import com.campusmov.platform.reputationincentivesservice.reputationincentives.domain.services.InfractionTrackerCommandService;
import com.campusmov.platform.reputationincentivesservice.reputationincentives.infrastructure.persistence.jpa.repositories.InfractionTrackerRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InfractionTrackerCommandServiceImpl implements InfractionTrackerCommandService {
    private final InfractionTrackerRepository infractionTrackerRepository;

    public InfractionTrackerCommandServiceImpl(InfractionTrackerRepository infractionTrackerRepository) {
        this.infractionTrackerRepository = infractionTrackerRepository;
    }


    @Override
    public Optional<InfractionTracker> handle(CreateInfractionTrackerCommand createInfractionTrackerCommand) {
        var infractionTracker = new InfractionTracker(createInfractionTrackerCommand);

        if (infractionTrackerRepository.existsByUserIdAndInfractionType(infractionTracker.getUserId(), infractionTracker.getInfractionType())){
            var newInfractionTracker = infractionTrackerRepository.findByUserIdAndInfractionType(infractionTracker.getUserId(), infractionTracker.getInfractionType());
            infractionTracker = newInfractionTracker.get();
            infractionTracker.incrementCounter();
        }

        infractionTrackerRepository.save(infractionTracker);
        return Optional.of(infractionTracker);
    }

    @Override
    public Optional<InfractionTracker> handleReset(CreateInfractionTrackerCommand createInfractionTrackerCommand) {
        var infractionTracker = new InfractionTracker(createInfractionTrackerCommand);
        var newInfractionTracker = infractionTrackerRepository.findByUserIdAndInfractionType(infractionTracker.getUserId(), infractionTracker.getInfractionType());
        infractionTracker = newInfractionTracker.get();
        infractionTracker.resetCounter();

        return Optional.of(infractionTracker);
    }
}
