package com.mycompany.servlets;

import com.mycompany.beans.User;
import com.mycompany.dao.DaoFactory;
import com.mycompany.dao.UserDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet({"/login"})
public class Login extends HttpServlet {

    private UserDaoImpl userDao; //variable user dao impl

    @Override
    public void init() throws ServletException { //on init
        DaoFactory daoFactory = DaoFactory.getInstance(); //get instance pour charger le driver et les param de connexion
        userDao = new UserDaoImpl(daoFactory); //on va créer un utilisateur dao impl en se connectant avec dao factory
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String login = request.getParameter("txtLogin"); //on recup les param dans la jsp à la requette post
        String password = request.getParameter("txtPassword"); //le login et le password

        User connectedUser = userDao.isValidLogin(login, password); //on recupere un utilisateur qu'on nomme connected user depuis userdao et methode isvalidlogin

        if (connectedUser != null){ //ensuite on teste si isvalidlogin reconnait donc si pas null

            HttpSession session = request.getSession(true); //création de la session
            session.setAttribute("connectedUser", connectedUser);

            response.sendRedirect("/index/listejoueur"); //permet la connexion
        } else { //sinon il reste sur la page
            response.sendRedirect("/index/login");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



        this.getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
    }
}
