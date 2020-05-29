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

@WebServlet({"/modifiertournoi"})
public class ModifierTournoi extends HttpServlet {
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
        int id = Integer.parseInt(request.getParameter("idtournoi"));


        tournoi.setNom(nom);
        tournoi.setCode(code);
        tournoi.setId(id);

        tournoiDao.modifier(tournoi);

        response.sendRedirect("/index/listetournoi");


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession( true );
        if ( session.getAttribute( "connectedUser" ) == null ) {
            response.sendRedirect( "/index/login" );
            return;
        }

        String id = request.getParameter("idtournoi");
        long idl = Long.parseLong(id);
        if(request.getParameter("action").equals("Modifier")){
            Tournoi tournoi = tournoiDao.lecture(idl);
            request.setAttribute("tournoi", tournoi);

            this.getServletContext().getRequestDispatcher("/WEB-INF/modifiertournoi.jsp").forward(request, response);


        } else {
            tournoiDao.supprimer(idl);
            response.sendRedirect("/index/listetournoi");
        }

    }
}
