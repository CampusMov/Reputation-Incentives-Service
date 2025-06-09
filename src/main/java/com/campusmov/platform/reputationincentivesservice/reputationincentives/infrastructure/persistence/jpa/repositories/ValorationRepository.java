package com.campusmov.platform.reputationincentivesservice.reputationincentives.infrastructure.persistence.jpa.repositories;

import com.campusmov.platform.reputationincentivesservice.reputationincentives.domain.model.aggregates.Valoration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ValorationRepository extends JpaRepository<Valoration,Long> {

}
