package com.campusmov.platform.reputationincentivesservice.reputationincentives.infrastructure.brokers.kafka;

import com.campusmov.platform.reputationincentivesservice.reputationincentives.domain.model.events.ValorationCreatedEvent;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Supplier;

@Configuration
public class ValorationEventSource {

    private final Queue<Message<?>> eventQueue = new LinkedList<>();

    @Bean
    public Supplier<Message<?>> valorationSupplier() {
        return this.eventQueue::poll;
    }

    public void publishEvent(ValorationCreatedEvent event) {
        this.eventQueue.add(MessageBuilder.withPayload(event).build());
    }

}
