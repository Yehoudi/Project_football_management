package com.matawan.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entité représentant un joueur dans une équipe.
 * Contient les informations d'un joueur, notamment son nom, sa position et
 * l'équipe à laquelle il appartient.
 */
@Entity
@Data
@NoArgsConstructor
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String position;

    @ManyToOne
    @JoinColumn(name = "team_id")
    @JsonIgnore // Pour eviter la récursion infinie lors de la sérialisation JSON
    private Team team;
}
