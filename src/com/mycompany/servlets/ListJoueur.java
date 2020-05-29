package com.mycompany.servlets;

import com.mycompany.dao.DaoFactory;
import com.mycompany.dao.JoueurDao;
import com.mycompany.dao.JoueurDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet({"/listejoueur"})
public class ListJoueur extends HttpServlet {
    private JoueurDao joueurDao;


    @Override
    public void init() throws ServletException {
        DaoFactory daoFactory = DaoFactory.getInstance();
        joueurDao = new JoueurDaoImpl(daoFactory);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



        String recherche = request.getParameter("txtSearch");

        request.setAttribute("joueurs", joueurDao.rechercher(recherche));




        this.getServletContext().getRequestDispatcher("/WEB-INF/listjoueur.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession( true ); //créer un session
        if ( session.getAttribute( "connectedUser" ) == null ) { //si pas utilisateur identifié alors
            response.sendRedirect( "/index/login" ); //redirection vers la page login
            return; //on sort pour ne pas exécuter le reste
        }

        request.setAttribute("joueurs", joueurDao.lister());



        this.getServletContext().getRequestDispatcher("/WEB-INF/listjoueur.jsp").forward(request, response);
    }
}
