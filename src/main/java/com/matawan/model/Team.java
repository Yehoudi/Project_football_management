package com.matawan.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

/**
 * Entité représentant une équipe avec un nom, un acronyme, un budget et une
 * liste de joueurs.
 */
@Entity
@Data
@NoArgsConstructor
public class Team {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String acronym;

    @Column(nullable = false)
    private Double budget;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private List<Player> joueurs = new ArrayList<>();

    /**
     * Constructeur permettant de définir une équipe avec un nom, un acronyme et un
     * budget
     * 
     * @param name    le nom de l'équipe
     * @param acronym l'acronyme de l'équipe
     * @param budget  le budget de l'équipe
     */
    public Team(String name, String acronym, Double budget) {
        this.name = name;
        this.acronym = acronym;
        this.budget = budget;
    }
}
