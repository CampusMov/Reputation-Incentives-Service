package com.campusmov.platform.reputationincentivesservice.reputationincentives.domain.model.aggregates;

import com.campusmov.platform.reputationincentivesservice.reputationincentives.domain.model.commands.CreateInfractionTrackerCommand;
import com.campusmov.platform.reputationincentivesservice.reputationincentives.domain.model.commands.CreatePenaltyCommand;
import com.campusmov.platform.reputationincentivesservice.reputationincentives.domain.model.valueobjects.InfractionType;
import com.campusmov.platform.reputationincentivesservice.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

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


    public Optional<CreatePenaltyCommand> validatePenalty() {
        return switch (this.infractionType) {
            case LATE_CANCELLATION -> {
                if (this.consecutiveCount >= InfractionType.LATE_CANCELLATION.getPenaltyCount2()) {
                    yield Optional.of(new CreatePenaltyCommand(this.userId, "SUSPENSION",
                            "ACTIVE", this.infractionType.name()));
                }
                if (this.consecutiveCount >= InfractionType.LATE_CANCELLATION.getPenaltyCount()) {
                    yield Optional.of(new CreatePenaltyCommand(this.userId, "WARNING",
                            "RECEIVED", this.infractionType.name()));
                }
                yield Optional.empty();
            }
            case REJECTED_PASSENGER -> {
                if (this.consecutiveCount >= InfractionType.REJECTED_PASSENGER.getPenaltyCount2()) {
                    yield Optional.of(new CreatePenaltyCommand(this.userId, "SUSPENSION",
                            "ACTIVE", this.infractionType.name()));
                }
                if (this.consecutiveCount >= InfractionType.REJECTED_PASSENGER.getPenaltyCount()) {
                    yield Optional.of(new CreatePenaltyCommand(this.userId, "WARNING",
                            "RECEIVED", this.infractionType.name()));
                }
                yield Optional.empty();
            }
            case LATE_COMMISSION_PAYMENT -> {
                if (this.consecutiveCount >= InfractionType.LATE_COMMISSION_PAYMENT.getPenaltyCount2()) {
                    yield Optional.of(new CreatePenaltyCommand(this.userId, "FINE",
                            "RECEIVED", this.infractionType.name()));
                }
                if (this.consecutiveCount >= InfractionType.LATE_COMMISSION_PAYMENT.getPenaltyCount()) {
                    yield Optional.of(new CreatePenaltyCommand(this.userId, "WARNING",
                            "RECEIVED", this.infractionType.name()));
                }
                yield Optional.empty();
            }
            case SLOW_DRIVER -> {
                if (this.consecutiveCount >= InfractionType.SLOW_DRIVER.getPenaltyCount2()) {
                    yield Optional.of(new CreatePenaltyCommand(this.userId, "SUSPENSION",
                            "ACTIVE", this.infractionType.name()));
                }
                if (this.consecutiveCount >= InfractionType.SLOW_DRIVER.getPenaltyCount()) {
                    yield Optional.of(new CreatePenaltyCommand(this.userId, "WARNING",
                            "RECEIVED", this.infractionType.name()));
                }
                yield Optional.empty();
            }
            default -> Optional.empty();
        };
    }

}
