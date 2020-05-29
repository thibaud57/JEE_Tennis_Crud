package com.mycompany.servlets;

import com.mycompany.beans.BeanException;
import com.mycompany.beans.Tournoi;
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

@WebServlet({"/ajoutertournoi"})
public class AjouterTournoi extends HttpServlet {
    private TournoiDao tournoiDao;

    @Override
    public void init() throws ServletException {
        DaoFactory daoFactory = DaoFactory.getInstance();
        tournoiDao = new TournoiDaoImpl(daoFactory);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Tournoi tournoi = new Tournoi();

        String nom = request.getParameter("txtNom");
        String code = request.getParameter("txtCode");

        tournoi.setNom(nom);
        tournoi.setCode(code);

        tournoiDao.ajouter(tournoi);

        response.sendRedirect("/index/listetournoi");


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession( true );
        if ( session.getAttribute( "connectedUser" ) == null ) {
            response.sendRedirect( "/index/login" );
            return;
        }


        this.getServletContext().getRequestDispatcher("/WEB-INF/ajoutertournoi.jsp").forward(request, response);
    }
}
