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

/**
 * Representa el agregado raíz del dominio de incentivos dentro del contexto de reputación.
 * <p>
 * Un {@code Incentive} se asocia a un usuario y define el tipo de recompensa, su estado
 * y una posible fecha de expiración. Este agregado maneja la lógica relacionada con
 * la creación y el ciclo de vida de un incentivo.
 * </p>
 *
 * @author
 * @version 1.0
 */
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

    /**
     * Crea un nuevo incentivo a partir de un comando de creación.
     * <p>
     * Asigna el estado inicial como {@link IncentiveStatus#ACTIVE} y define
     * la fecha de expiración según el tipo de recompensa.
     * </p>
     *
     * @param command el comando que contiene los datos necesarios para crear el incentivo
     */

    public Incentive(CreateIncentiveCommand command) {
        this.userId = command.userId();
        this.rewardType = typeFromStringToEnum(command.rewardType());
        this.status = IncentiveStatus.ACTIVE;
        this.expirationDate = assignExpirationDate(this.rewardType);
    }

    /**
     * Convierte una cadena de texto al tipo enumerado {@link RewardType}.
     *
     * @param rewardType el tipo de recompensa en formato texto
     * @return el valor correspondiente del enumerado {@link RewardType}
     * @throws IllegalArgumentException si el texto no coincide con ningún valor del enumerado
     */

    public RewardType typeFromStringToEnum(String rewardType) {
        return RewardType.valueOf(rewardType.toUpperCase());
    }

    /**
     * Asigna una fecha de expiración según el tipo de recompensa.
     * <p>
     * Si el tipo de recompensa es {@code BONUS_CREDITS}, el incentivo se marca
     * como {@link IncentiveStatus#REDEEMED} y no tiene fecha de expiración.
     * En caso contrario, se establece una expiración a 12 horas desde el momento actual.
     * </p>
     *
     * @param rewardType el tipo de recompensa para determinar la expiración
     * @return la fecha de expiración asignada o {@code null} si no aplica
     */
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

    /**
     * Verifica si el incentivo ha expirado.
     *
     * @return {@code true} si la fecha actual es posterior a la fecha de expiración,
     *         {@code false} en caso contrario o si el incentivo no tiene expiración
     */

    public boolean isExpired() {
        return this.expirationDate != null && LocalDateTime.now().isAfter(this.expirationDate);
    }

}
