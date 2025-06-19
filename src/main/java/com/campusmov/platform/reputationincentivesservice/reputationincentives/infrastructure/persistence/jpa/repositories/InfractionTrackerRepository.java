package com.campusmov.platform.reputationincentivesservice.reputationincentives.infrastructure.persistence.jpa.repositories;

import com.campusmov.platform.reputationincentivesservice.reputationincentives.domain.model.aggregates.InfractionTracker;
import com.campusmov.platform.reputationincentivesservice.reputationincentives.domain.model.valueobjects.InfractionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InfractionTrackerRepository extends JpaRepository<InfractionTracker,String> {
    boolean existsByUserIdAndInfractionType(String userId, InfractionType infractionType);
    Optional<InfractionTracker> findByUserIdAndInfractionType(String userId, InfractionType infractionType);
}
