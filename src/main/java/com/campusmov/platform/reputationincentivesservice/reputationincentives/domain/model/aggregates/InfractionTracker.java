package com.campusmov.platform.reputationincentivesservice.reputationincentives.domain.model.aggregates;

import com.campusmov.platform.reputationincentivesservice.reputationincentives.domain.model.commands.CreateInfractionTrackerCommand;
import com.campusmov.platform.reputationincentivesservice.reputationincentives.domain.model.valueobjects.InfractionType;
import com.campusmov.platform.reputationincentivesservice.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class InfractionTracker extends AuditableAbstractAggregateRoot<InfractionTracker> {

    private String userId;
    private InfractionType infractionType;
    private Integer consecutiveCount;

    public InfractionTracker() {

    }

    public InfractionTracker(CreateInfractionTrackerCommand createInfractionTrackerRecord) {
        this.userId = createInfractionTrackerRecord.userId();
        this.infractionType = fromStringToEnum(createInfractionTrackerRecord.infractionType());
        this.consecutiveCount = 1;
    }

    public void incrementCounter() {
        this.consecutiveCount++;
    }
    public void resetCounter() {
        consecutiveCount = 0;
    }

    public InfractionType fromStringToEnum(String infractionType) {
        return InfractionType.valueOf(infractionType.toUpperCase());
    }




}
