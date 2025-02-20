package com.matawan.services;

import com.matawan.model.Team;
import com.matawan.repository.TeamRepository;
import com.matawan.service.TeamService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import java.util.Collections;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Classe de test pour le service TeamService.
 * Teste les méthodes de gestion des équipes, incluant la récupération,
 * l'enregistrement, la mise à jour et la suppression.
 */
@ExtendWith(MockitoExtension.class)
public class TeamServiceTest {

    @Mock
    private TeamRepository teamRepository;

    @InjectMocks
    private TeamService teamService;

    /**
     * Teste la méthode getAllTeams() pour s'assurer qu'elle renvoie les équipes
     * correctement paginées.
     */
    @Test
    public void testGetAllTeams() {
        Team team = new Team();
        team.setName("OGC Nice");
        Page<Team> teamPage = new PageImpl<>(Collections.singletonList(team));

        when(teamRepository.findAll(any(PageRequest.class))).thenReturn(teamPage);

        Page<Team> result = teamService.getAllTeams(PageRequest.of(0, 10));

        assertEquals(1, result.getTotalElements());
        assertEquals("OGC Nice", result.getContent().get(0).getName());
    }

    /**
     * Teste la méthode saveTeam() pour vérifier l'enregistrement d'une nouvelle
     * équipe.
     */
    @Test
    public void testSaveTeam() {
        Team team = new Team();
        team.setName("OGC Nice");

        when(teamRepository.save(any(Team.class))).thenReturn(team);

        Team result = teamService.saveTeam(team);

        assertEquals("OGC Nice", result.getName());
    }

    /**
     * Teste la méthode updateTeam() pour s'assurer qu'une équipe est correctement
     * mise à jour.
     */
    @Test
    public void testUpdateTeam() {
        Team existingTeam = new Team();
        existingTeam.setId(1L);
        existingTeam.setName("OGC Nice");

        Team updatedTeam = new Team();
        updatedTeam.setName("OGC Nice Updated");

        // Simule le comportement du repository pour retourner l'équipe existante et la
        // sauvegarder après mise à jour
        when(teamRepository.findById(1L)).thenReturn(Optional.of(existingTeam));
        when(teamRepository.save(any(Team.class))).thenReturn(existingTeam);

        Team result = teamService.updateTeam(1L, updatedTeam);

        assertEquals("OGC Nice Updated", result.getName());
    }

    /**
     * Teste la méthode deleteTeam() pour vérifier la suppression d'une équipe.
     */
    @Test
    public void testDeleteTeam() {
        teamService.deleteTeam(1L);
        verify(teamRepository, times(1)).deleteById(1L);
    }
}
