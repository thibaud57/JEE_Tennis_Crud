package com.mycompany.dao;

import com.mycompany.beans.Joueur;

import java.util.List;

public interface JoueurDao {
    void ajouter( Joueur joueur );

    List<Joueur> lister();

    Joueur lecture(Long id);
    void modifier(Joueur joueur);

    void supprimer(Long id);

    List<Joueur> rechercher(String chaine);
}
