package com.campusmov.platform.reputationincentivesservice.reputationincentives.infrastructure.persistence.jpa.repositories;

import com.campusmov.platform.reputationincentivesservice.reputationincentives.domain.model.aggregates.Incentive;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IncentiveRepository extends JpaRepository<Incentive,String> {

}
