package com.mycompany.servlets;

import com.mycompany.dao.DaoFactory;
import com.mycompany.dao.TournoiDao;
import com.mycompany.dao.TournoiDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet({"/listetournoi"})
public class ListTournoi extends HttpServlet {
    private TournoiDao tournoiDao;


    @Override
    public void init() throws ServletException {
        DaoFactory daoFactory = DaoFactory.getInstance();
        tournoiDao = new TournoiDaoImpl(daoFactory);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



        String recherche = request.getParameter("txtSearch");

        request.setAttribute("tournois", tournoiDao.rechercher(recherche));




        this.getServletContext().getRequestDispatcher("/WEB-INF/listtournoi.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession( true ); //créer un session
        if ( session.getAttribute( "connectedUser" ) == null ) { //si pas utilisateur identifié alors
            response.sendRedirect( "/index/login" ); //redirection vers la page login
            return; //on sort pour ne pas exécuter le reste
        }

        request.setAttribute("tournois", tournoiDao.lister());



        this.getServletContext().getRequestDispatcher("/WEB-INF/listtournoi.jsp").forward(request, response);
    }
}
