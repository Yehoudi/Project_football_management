package com.matawan.controller;

import com.matawan.model.Team;
import com.matawan.model.Player;
import com.matawan.service.TeamService;
import com.matawan.service.PlayerService;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Contrôleur pour la gestion des équipes et des joueurs.
 * Fournit des points de terminaison pour récupérer, ajouter, modifier et supprimer des équipes et des joueurs.
 */
@RestController
@RequestMapping("/api/teams")
@Tag(name = "Teams", description = "Endpoints pour gérer les équipes et les joueurs")
public class TeamController {

    @Autowired
    private TeamService teamService;

    @Autowired
    private PlayerService playerService;

    /**
     * Récupère toutes les équipes avec pagination.
     * @param pageable les paramètres de pagination
     * @return une réponse contenant une page d'équipes
     */
    @Operation(summary = "Lister toutes les équipes", description = "Retourne la liste des équipes avec pagination.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Liste des équipes récupérée avec succès"),
        @ApiResponse(responseCode = "500", description = "Erreur serveur")
    })
    @GetMapping
    public ResponseEntity<Page<Team>> getAllTeams(Pageable pageable) {
        Page<Team> teams = teamService.getAllTeams(pageable);
        return ResponseEntity.ok(teams);
    }

    /**
     * Ajoute une nouvelle équipe avec ou sans joueurs.
     * @param team l'équipe à ajouter
     * @return une réponse contenant l'équipe ajoutée
     */
    @Operation(summary = "Ajouter une équipe", description = "Ajoute une nouvelle équipe avec ou sans joueurs.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Équipe créée avec succès"),
        @ApiResponse(responseCode = "400", description = "Requête invalide")
    })
    @PostMapping
    public ResponseEntity<Team> addTeam(@RequestBody Team team) {
        Team savedTeam = teamService.saveTeam(team);
        return ResponseEntity.status(201).body(savedTeam);
    }

    /**
     * Modifie une équipe existante.
     * @param id l'ID de l'équipe à modifier
     * @param updatedTeam les nouvelles données de l'équipe
     * @return une réponse contenant l'équipe mise à jour
     */
    @Operation(summary = "Modifier une équipe", description = "Modifie les informations d'une équipe existante.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Équipe mise à jour avec succès"),
        @ApiResponse(responseCode = "404", description = "Équipe non trouvée"),
        @ApiResponse(responseCode = "400", description = "Requête invalide")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Team> updateTeam(@PathVariable Long id, @RequestBody Team updatedTeam) {
        Team updated = teamService.updateTeam(id, updatedTeam);
        return ResponseEntity.ok(updated);
    }

    /**
     * Supprime une équipe existante.
     * @param id l'ID de l'équipe à supprimer
     * @return une réponse vide avec le statut 204 (No Content)
     */
    @Operation(summary = "Supprimer une équipe", description = "Supprime une équipe existante.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Équipe supprimée avec succès"),
        @ApiResponse(responseCode = "404", description = "Équipe non trouvée")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeam(@PathVariable Long id) {
        teamService.deleteTeam(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Récupère tous les joueurs.
     * @return une liste de tous les joueurs enregistrés
     */
    @Operation(summary = "Lister tous les joueurs", description = "Retourne la liste de tous les joueurs.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Liste des joueurs récupérée avec succès"),
        @ApiResponse(responseCode = "500", description = "Erreur serveur")
    })
    @GetMapping("/players")
    public ResponseEntity<List<Player>> getAllPlayers() {
        List<Player> players = playerService.getAllPlayers();
        return ResponseEntity.ok(players);
    }


    @Operation(summary = "Récupérer une équipe par ID", description = "Retourne une équipe spécifique.")
@ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Équipe trouvée"),
    @ApiResponse(responseCode = "404", description = "Équipe non trouvée")
})
@GetMapping("/{teamId}")
public ResponseEntity<Team> getTeamById(@PathVariable Long teamId) {
    Team team = teamService.getTeamById(teamId);
    return ResponseEntity.ok(team);
}

@Operation(summary = "Récupérer un joueur par ID", description = "Retourne un joueur spécifique.")
@ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Joueur trouvé"),
    @ApiResponse(responseCode = "404", description = "Joueur non trouvé")
})
@GetMapping("/players/{playerId}")
public ResponseEntity<Player> getPlayerById(@PathVariable Long playerId) {
    Player player = playerService.getPlayerById(playerId);
    return ResponseEntity.ok(player);
}


    /**
     * Modifie un joueur existant dans une équipe.
     * @param playerId l'ID du joueur à modifier
     * @param updatedPlayer les nouvelles données du joueur
     * @return une réponse contenant le joueur mis à jour
     */
    @Operation(summary = "Modifier un joueur", description = "Met à jour les informations d'un joueur existant.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Joueur mis à jour avec succès"),
        @ApiResponse(responseCode = "404", description = "Joueur non trouvé"),
        @ApiResponse(responseCode = "400", description = "Requête invalide")
    })
    @PutMapping("/players/{playerId}")
    public ResponseEntity<Player> updatePlayer(@PathVariable Long playerId, @RequestBody Player updatedPlayer) {
        Player updated = playerService.updatePlayer(playerId, updatedPlayer);
        return ResponseEntity.ok(updated);
    }

    /**
     * Supprime un joueur existant.
     * @param playerId l'ID du joueur à supprimer
     * @return une réponse vide avec le statut 204 (No Content)
     */
    @Operation(summary = "Supprimer un joueur", description = "Supprime un joueur existant d'une équipe.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Joueur supprimé avec succès"),
        @ApiResponse(responseCode = "404", description = "Joueur non trouvé")
    })
    @DeleteMapping("/players/{playerId}")
    public ResponseEntity<Void> deletePlayer(@PathVariable Long playerId) {
        playerService.deletePlayer(playerId);
        return ResponseEntity.noContent().build();
    }
}
