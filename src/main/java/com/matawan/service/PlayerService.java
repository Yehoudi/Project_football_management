package com.matawan.service;

import com.matawan.model.Player;
import com.matawan.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service pour la gestion des joueurs.
 * Contient des méthodes pour mettre à jour et supprimer les joueurs dans la
 * base de données.
 */
@Service
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    /**
     * Met à jour un joueur existant.
     * 
     * @param id l'ID du joueur à mettre à jour
     * @param updatedPlayer les nouvelles informations du joueur
     * @return le joueur mis à jour
     */
    public Player updatePlayer(Long id, Player updatedPlayer) {
        return playerRepository.findById(id).map(player -> {
            player.setName(updatedPlayer.getName());
            player.setPosition(updatedPlayer.getPosition());
            return playerRepository.save(player);
        }).orElseThrow(() -> new RuntimeException("Player not found with id: " + id));
    }

    /**
     * Supprime un joueur existant.
     * 
     * @param id l'ID du joueur à supprimer
     */
    public void deletePlayer(Long id) {
        playerRepository.deleteById(id);
    }
}
