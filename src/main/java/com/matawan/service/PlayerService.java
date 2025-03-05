package com.matawan.service;

import com.matawan.model.Player;
import com.matawan.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service pour la gestion des joueurs.
 * Contient des méthodes pour récupérer, mettre à jour et supprimer les joueurs dans la base de données.
 */
@Service
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    /**
     * Récupère tous les joueurs.
     * 
     * @return une liste de tous les joueurs enregistrés
     */
    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

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

    public Player getPlayerById(Long id) {
        return playerRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Joueur non trouvé avec l'ID : " + id));
    }
    
}
