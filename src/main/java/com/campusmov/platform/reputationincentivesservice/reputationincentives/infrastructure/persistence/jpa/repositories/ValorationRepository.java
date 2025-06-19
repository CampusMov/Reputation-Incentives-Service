package com.campusmov.platform.reputationincentivesservice.reputationincentives.infrastructure.persistence.jpa.repositories;

import com.campusmov.platform.reputationincentivesservice.reputationincentives.domain.model.aggregates.Valoration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface ValorationRepository extends JpaRepository<Valoration,String> {
    Collection<Valoration> findAllByUserId(String userId);
}
