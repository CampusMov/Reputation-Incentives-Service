package com.campusmov.platform.reputationincentivesservice.reputationincentives.application.internal.outboundservices;

import com.campusmov.platform.reputationincentivesservice.reputationincentives.domain.model.events.ValorationCreatedEvent;
import com.campusmov.platform.reputationincentivesservice.reputationincentives.infrastructure.brokers.kafka.ValorationEventSource;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionalEventListener;

@Service
@RequiredArgsConstructor
public class ValorationEventPublisherService {
    private final ValorationEventSource valorationEventSource;

    @TransactionalEventListener
    public void handleValorationCreatedEvent(ValorationCreatedEvent event) {
        valorationEventSource.publishEvent(event);
    }
}
