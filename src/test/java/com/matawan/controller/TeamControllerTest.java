package com.matawan.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.matawan.model.Team;
import com.matawan.service.TeamService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Classe de test pour le contrôleur TeamController.
 * Teste les fonctionnalités de l'API REST pour la gestion des équipes.
 */
@SpringBootTest
@ActiveProfiles("test")
public class TeamControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @MockitoBean
    private TeamService teamService;

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * Prépare l'environnement de test avant chaque test.
     * Initialise le MockMvc pour simuler les requêtes HTTP.
     */
    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    /**
     * Teste l'ajout d'une équipe via l'API REST.
     * Vérifie que l'équipe est correctement enregistrée et retourne une réponse
     * avec le bon statut et données.
     * 
     * @throws Exception si une erreur se produit lors de l'exécution du test.
     */
    @Test
    public void testAddTeam() throws Exception {
        // Arrange
        Team team = new Team();
        team.setName("OGC Nice");

        when(teamService.saveTeam(Mockito.any(Team.class))).thenReturn(team);

        // Act & Assert
        mockMvc.perform(post("/api/teams")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(team)))
                .andExpect(status().isCreated()) // Vérifie le statut HTTP 201
                .andExpect(jsonPath("$.name").value("OGC Nice"));
    }
}
