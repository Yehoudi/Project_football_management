package com.matawan.repository;

import com.matawan.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository pour l'entité Player.
 * Fournit des méthodes pour effectuer des opérations CRUD sur les joueurs dans
 * la base de données.
 */
@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
}
