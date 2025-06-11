package com.campusmov.platform.reputationincentivesservice.reputationincentives.domain.model.aggregates;

import com.campusmov.platform.reputationincentivesservice.reputationincentives.domain.model.commands.CreateIncentiveCommand;
import com.campusmov.platform.reputationincentivesservice.reputationincentives.domain.model.valueobjects.IncentiveStatus;
import com.campusmov.platform.reputationincentivesservice.reputationincentives.domain.model.valueobjects.RewardType;
import com.campusmov.platform.reputationincentivesservice.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Incentive extends AuditableAbstractAggregateRoot<Incentive> {

    @NotBlank
    private String userId;

    @NotBlank
    private IncentiveStatus status;

    @NotBlank
    private RewardType rewardType;

    private LocalDateTime expirationDate;

    public Incentive() {

    }

    public Incentive(CreateIncentiveCommand command) {
        this.userId = command.userId();
        this.rewardType = typeFromStringToEnum(command.rewardType());
        this.status = IncentiveStatus.ACTIVE;
        this.expirationDate = assignExpirationDate(this.rewardType);
    }

    public RewardType typeFromStringToEnum(String rewardType) {
        return RewardType.valueOf(rewardType.toUpperCase());
    }

    public LocalDateTime assignExpirationDate(RewardType rewardType) {
        if(rewardType == RewardType.BONUS_CREDITS) {
            this.status = IncentiveStatus.REDEEMED;
            return null;
        }
        else {
            return LocalDateTime.now().plusHours(12);
        }
        //return null;
    }

    public boolean isExpired() {
        return this.expirationDate != null && LocalDateTime.now().isAfter(this.expirationDate);
    }

}
