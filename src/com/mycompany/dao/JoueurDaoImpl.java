package com.mycompany.dao;

import com.mycompany.beans.Joueur;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JoueurDaoImpl implements JoueurDao {

    private DaoFactory daoFactory;

    public JoueurDaoImpl(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }


    @Override
    public void ajouter(Joueur joueur) {
        Connection connexion = null;
        PreparedStatement preparedStatemt = null;

        try {
            connexion = daoFactory.getConnection();

            preparedStatemt = connexion.prepareStatement("INSERT INTO joueur(nom, prenom, sexe) VALUES (?, ?, ?);");
            preparedStatemt.setString(1, joueur.getNom());
            preparedStatemt.setString(2, joueur.getPrenom());
            preparedStatemt.setString(3, joueur.getSexe());
            preparedStatemt.executeUpdate();

            System.out.println("oco");
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    @Override
    public List<Joueur> lister() {
        List<Joueur> joueurs = new ArrayList<>();
        Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;

        try {

            connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            resultat = statement.executeQuery("SELECT * FROM joueur;");

            while (resultat.next()) {
                int id = resultat.getInt("id");
                String nom = resultat.getString("nom");
                String prenom = resultat.getString("prenom");
                String sexe = resultat.getString("sexe");

                Joueur joueur = new Joueur();
                joueur.setId(id);
                joueur.setNom(nom);
                joueur.setPrenom(prenom);
                joueur.setSexe(sexe);

                joueurs.add(joueur);
            }
            return joueurs;
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public Joueur lecture(Long id) {
        Connection connexion = null;
        PreparedStatement statement = null;

        try {
            connexion = daoFactory.getConnection();
            statement = connexion.prepareStatement("SELECT * FROM JOUEUR WHERE ID=?");

            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {

                return new Joueur(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("sexe")
                );
            } else {
                return null;
            }
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }

    }

    @Override
    public void modifier(Joueur joueur) {
        Connection connexion = null;
        PreparedStatement statement = null;

        try {

            connexion = daoFactory.getConnection();
            statement = connexion.prepareStatement("UPDATE JOUEUR SET NOM=?,PRENOM=?, SEXE=? WHERE ID=?");


            statement.setString(1, joueur.getNom());
            statement.setString(2, joueur.getPrenom());
            statement.setString(3, joueur.getSexe());
            long idl = Long.valueOf(joueur.getId());
            statement.setLong(4, idl);
            statement.executeUpdate();
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public void supprimer(Long id) {
        Connection connexion = null;
        PreparedStatement statement = null;

        try {

            connexion = daoFactory.getConnection();

            statement = connexion.prepareStatement("DELETE FROM JOUEUR WHERE ID=?");
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public List<Joueur> rechercher(String recherche) {
        List<Joueur> joueurs = new ArrayList<>();
        Connection connexion = null;
        PreparedStatement statement = null;
        ResultSet resultat = null;

        try {

            connexion = daoFactory.getConnection();
            String strSql = "SELECT * FROM joueur WHERE nom LIKE ? OR prenom like ?";
            statement = connexion.prepareStatement(strSql);
            statement.setString(1, "%" + recherche + "%");
            statement.setString(2, "%" + recherche + "%");

            resultat = statement.executeQuery();

            while (resultat.next()) {
                int id = resultat.getInt("id");
                String nom = resultat.getString("nom");
                String prenom = resultat.getString("prenom");
                String sexe = resultat.getString("sexe");

                Joueur joueur = new Joueur();
                joueur.setId(id);
                joueur.setNom(nom);
                joueur.setPrenom(prenom);
                joueur.setSexe(sexe);

                joueurs.add(joueur);
            }
            return joueurs;
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

}
