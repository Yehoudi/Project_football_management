package com.matawan.service;

import com.matawan.model.Team;
import com.matawan.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Service pour la gestion des équipes.
 * Contient des méthodes pour récupérer, sauvegarder, mettre à jour et supprimer des équipes.
 */
@Service
public class TeamService {

    @Autowired
    private TeamRepository teamRepository;

    /**
     * Récupère toutes les équipes avec pagination.
     * @param pageable les paramètres de pagination
     * @return une page d'équipes
     */
    public Page<Team> getAllTeams(Pageable pageable) {
        return teamRepository.findAll(pageable);
    }

    /**
     * Sauvegarde une nouvelle équipe dans la base de données.
     * @param team l'équipe à sauvegarder
     * @return l'équipe sauvegardée
     */
    public Team saveTeam(Team team) {
        team.getJoueurs().forEach(player -> player.setTeam(team));
        return teamRepository.save(team);
    }

    /**
     * Met à jour une équipe existante.
     * @param id l'ID de l'équipe à mettre à jour
     * @param updatedTeam les nouvelles informations de l'équipe
     * @return l'équipe mise à jour
     */
    public Team updateTeam(Long id, Team updatedTeam) {
        return teamRepository.findById(id).map(team -> {
            team.setName(updatedTeam.getName());
            team.setAcronym(updatedTeam.getAcronym());
            team.setBudget(updatedTeam.getBudget());
            team.getJoueurs().clear();
            updatedTeam.getJoueurs().forEach(player -> {
                player.setTeam(team);
                team.getJoueurs().add(player);
            });
            return teamRepository.save(team);
        }).orElseThrow(() -> new RuntimeException("Team not found with id: " + id));
    }

    /**
     * Supprime une équipe de la base de données.
     * @param id l'ID de l'équipe à supprimer
     */
    public void deleteTeam(Long id) {
        teamRepository.deleteById(id);
    }

    public Team getTeamById(Long id) {
        return teamRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Équipe non trouvée avec l'ID : " + id));
    }
    
}
