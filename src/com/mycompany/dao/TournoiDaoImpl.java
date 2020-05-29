package com.mycompany.dao;

import com.mycompany.beans.Tournoi;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TournoiDaoImpl implements TournoiDao {

    private DaoFactory daoFactory;

    public TournoiDaoImpl(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }


    //add
    @Override
    public void ajouter(Tournoi Tournoi) {
        Connection connexion = null;
        PreparedStatement preparedStatemt = null;

        try {
            connexion = daoFactory.getConnection();

            preparedStatemt = connexion.prepareStatement("INSERT INTO Tournoi(nom, code) VALUES (?, ?);");
            preparedStatemt.setString(1, Tournoi.getNom());
            preparedStatemt.setString(2, Tournoi.getCode());
            preparedStatemt.executeUpdate();

            System.out.println("oco");
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    //read all
    @Override
    public List<Tournoi> lister() {
        List<Tournoi> Tournois = new ArrayList<>();
        Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;

        try {

            connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            resultat = statement.executeQuery("SELECT * FROM Tournoi;");

            while (resultat.next()) {
                int id = resultat.getInt("id");
                String nom = resultat.getString("nom");
                String code = resultat.getString("code");

                Tournoi Tournoi = new Tournoi();
                Tournoi.setId(id);
                Tournoi.setNom(nom);
                Tournoi.setCode(code);

                Tournois.add(Tournoi);
            }
            return Tournois;
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

    //read by id
    @Override
    public Tournoi lecture(Long id) {
        Connection connexion = null;
        PreparedStatement statement = null;

        try {
            connexion = daoFactory.getConnection();
            statement = connexion.prepareStatement("SELECT * FROM Tournoi WHERE ID=?");

            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {

                return new Tournoi(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("code")
                );
            } else {
                return null;
            }
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }

    }

    //update
    @Override
    public void modifier(Tournoi Tournoi) {
        Connection connexion = null;
        PreparedStatement statement = null;

        try {

            connexion = daoFactory.getConnection();
            statement = connexion.prepareStatement("UPDATE Tournoi SET NOM=?,CODE=? WHERE ID=?");


            statement.setString(1, Tournoi.getNom());
            statement.setString(2, Tournoi.getCode());
            long idl = Long.valueOf(Tournoi.getId());
            statement.setLong(3, idl);
            statement.executeUpdate();
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

    //delete
    @Override
    public void supprimer(Long id) {
        Connection connexion = null;
        PreparedStatement statement = null;

        try {

            connexion = daoFactory.getConnection();

            statement = connexion.prepareStatement("DELETE FROM Tournoi WHERE ID=?");
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

    //search
    @Override
    public List<Tournoi> rechercher(String recherche) {
        List<Tournoi> Tournois = new ArrayList<>();
        Connection connexion = null;
        PreparedStatement statement = null;
        ResultSet resultat = null;

        try {

            connexion = daoFactory.getConnection();
            String strSql = "SELECT * FROM Tournoi WHERE nom LIKE ? OR code like ?";
            statement = connexion.prepareStatement(strSql);
            statement.setString(1, "%" + recherche + "%");
            statement.setString(2, "%" + recherche + "%");

            resultat = statement.executeQuery();

            while (resultat.next()) {
                int id = resultat.getInt("id");
                String nom = resultat.getString("nom");
                String code = resultat.getString("code");

                Tournoi Tournoi = new Tournoi();
                Tournoi.setId(id);
                Tournoi.setNom(nom);
                Tournoi.setCode(code);

                Tournois.add(Tournoi);
            }
            return Tournois;
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

}
