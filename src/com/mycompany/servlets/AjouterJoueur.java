package com.mycompany.servlets;

import com.mycompany.beans.BeanException;
import com.mycompany.beans.Joueur;
import com.mycompany.dao.DaoFactory;
import com.mycompany.dao.JoueurDao;
import com.mycompany.dao.JoueurDaoImpl;
import com.mycompany.dao.UserDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet({"/ajouterjoueur"})
public class AjouterJoueur extends HttpServlet {
    private JoueurDao joueurDao;

    @Override
    public void init() throws ServletException {
        DaoFactory daoFactory = DaoFactory.getInstance();
        joueurDao = new JoueurDaoImpl(daoFactory);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Joueur joueur = new Joueur();

        String nom = request.getParameter("txtNom");
        String prenom = request.getParameter("txtPrenom");
        String sexe = request.getParameter("txtSexe");

        try {
            joueur.setNom(nom);
            joueur.setPrenom(prenom);
            joueur.setSexe(sexe);

            joueurDao.ajouter(joueur);

            response.sendRedirect("/index/listejoueur");
        } catch (BeanException e) {
            request.setAttribute("erreur", e.getMessage());
            this.getServletContext().getRequestDispatcher("/WEB-INF/modifierjoueur.jsp").forward(request, response);
        }




    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession( true );
        if ( session.getAttribute( "connectedUser" ) == null ) {
            response.sendRedirect( "/index/login" );
            return;
        }


        this.getServletContext().getRequestDispatcher("/WEB-INF/ajouterjoueur.jsp").forward(request, response);
    }
}
