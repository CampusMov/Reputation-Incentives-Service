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
        this.description = buildDescription(createPenaltyCommand.infractionType());

    }

    public PenaltyType typeFromStringToEnum(String penaltyType) {
        return PenaltyType.valueOf(penaltyType.toUpperCase());
    }
    public PenaltyStatus statusFromStringToEnum(String penaltyStatus) {
        return PenaltyStatus.valueOf(penaltyStatus.toUpperCase());
    }

    public String buildDescription(String infractionType) {
        if (infractionType.equals("LATE_CANCELLATION") ) {
            return "Late cancellation penalty applied. Please be aware of the cancellation policy.";
        } else if (infractionType.equals("REJECTED_PASSENGER")) {
            return "Penalty for rejecting a passenger. Ensure to follow the platform's guidelines.";
        } else if (infractionType.equals("LATE_COMMISSION_PAYMENT")) {
            return "Late commission payment penalty. Please ensure timely payments in the future.";
        } else if (infractionType.equals("SLOW_DRIVER")) {
            return "Penalty for slow driving. Please maintain a reasonable speed to ensure safety.";
        } else {
            return "Penalty applied for an unspecified infraction.";
        }
    }


}
