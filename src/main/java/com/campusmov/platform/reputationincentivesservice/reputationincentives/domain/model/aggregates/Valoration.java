package com.campusmov.platform.reputationincentivesservice.reputationincentives.domain.model.aggregates;

import com.campusmov.platform.reputationincentivesservice.reputationincentives.domain.model.commands.CreateValorationCommand;
import com.campusmov.platform.reputationincentivesservice.reputationincentives.domain.model.events.ValorationCreatedEvent;
import com.campusmov.platform.reputationincentivesservice.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Valoration extends AuditableAbstractAggregateRoot<Valoration> {

    @NotBlank
    private String userId;

    @NotBlank
    private String senderId;

    @NotNull
    private Double reputationScore;

    @NotBlank
    private String Message;

    public Valoration() {

    }

    public Valoration(CreateValorationCommand createValorationCommand) {
        this.userId = createValorationCommand.userId();
        this.senderId = createValorationCommand.senderId();
        this.reputationScore = createValorationCommand.reputationScore();
        this.Message = createValorationCommand.message();
    }

    public void sendValorationCreatedEvent() {
        ValorationCreatedEvent event = new ValorationCreatedEvent(
                this,
                this.getId(),
                this.userId,
                this.senderId,
                this.reputationScore,
                this.Message
        );
        registerEvent(event);
    }

}
