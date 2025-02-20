-- Crée la table des équipes (Team)
CREATE TABLE team (
    id SERIAL PRIMARY KEY, 
    name VARCHAR(255) NOT NULL, 
    acronym VARCHAR(10) NOT NULL,
    budget NUMERIC(15, 2) NOT NULL
);

-- Crée la table des joueurs (Player)
CREATE TABLE player (
    id SERIAL PRIMARY KEY, 
    name VARCHAR(255) NOT NULL, 
    position VARCHAR(50) NOT NULL, 
    team_id INT, 
    CONSTRAINT fk_team FOREIGN KEY (team_id) REFERENCES team (id) ON DELETE CASCADE
);

-- Ajoute des données initiales pour tester
-- Vous pouvez supprimer ou modifier ces données selon vos besoins.

-- Ajout d'une équipe de test
INSERT INTO team (name, acronym, budget) VALUES
('OGC Nice', 'OGCN', 7000000.00);

-- Ajout de joueurs associés à cette équipe
INSERT INTO player (name, position, team_id) VALUES
('Jean-Clair Todibo', 'Defenseur', 1),
('Dante Bonfim', 'Defenseur central', 1),
('Gaetan Laborde', 'Attaquant', 1),
('Terem Moffi', 'Attaquant', 1),
('Evann Guessand', 'Attaquant', 1),
('Teddy Boulhendi', 'Gardien de but', 1),
('Antoine Mendy', 'Arriere droit', 1),
('Joe Bryan', 'Arriere gauche', 1),
('Pablo Rosario', 'Milieu Defensif', 1),
('Morgan Sanson', 'Milieu de terrain', 1);
