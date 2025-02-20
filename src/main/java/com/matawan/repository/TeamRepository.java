package com.matawan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.matawan.model.Team;

/**
 * Repository pour l'entité Team.
 * Fournit des méthodes pour effectuer des opérations CRUD sur les équipes dans
 * la base de données.
 */
@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
}
