package com.campusmov.platform.reputationincentivesservice.reputationincentives.domain.model.entities;

import com.campusmov.platform.reputationincentivesservice.reputationincentives.domain.model.commands.CreatePenaltyCommand;
import com.campusmov.platform.reputationincentivesservice.reputationincentives.domain.model.valueobjects.PenaltyStatus;
import com.campusmov.platform.reputationincentivesservice.reputationincentives.domain.model.valueobjects.PenaltyType;
import com.campusmov.platform.reputationincentivesservice.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Penalty extends AuditableAbstractAggregateRoot<Penalty> {

    private String userId;
    private PenaltyType type;
    private PenaltyStatus status;
    private String description;

    public Penalty() {
    }

    public Penalty(CreatePenaltyCommand createPenaltyCommand) {
        this.userId = createPenaltyCommand.userId();
        this.type = typeFromStringToEnum(createPenaltyCommand.type());
        this.status = statusFromStringToEnum(createPenaltyCommand.status());
        this.description = createPenaltyCommand.description();

    }

    public PenaltyType typeFromStringToEnum(String penaltyType) {
        return PenaltyType.valueOf(penaltyType.toUpperCase());
    }
    public PenaltyStatus statusFromStringToEnum(String penaltyStatus) {
        return PenaltyStatus.valueOf(penaltyStatus.toUpperCase());
    }


}
