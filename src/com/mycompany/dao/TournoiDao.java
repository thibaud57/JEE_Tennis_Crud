package com.mycompany.dao;

import com.mycompany.beans.Joueur;
import com.mycompany.beans.Tournoi;

import java.util.List;

public interface TournoiDao {
    //add
    void ajouter(Tournoi tournoi);

    //read all
    List<Tournoi> lister();
    //read by id
    Tournoi lecture(Long id);
    void modifier(Tournoi joueur);

    //delete
    void supprimer(Long id);

    //search
    List<Tournoi> rechercher(String chaine);
}
