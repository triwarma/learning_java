package org.dhana.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.dhana.models.UserModel;
import org.dhana.repositorys.UserRepository;

import java.io.IOException;

@WebServlet("/register")
public class RegistrasiController extends HttpServlet {
    private UserRepository userRepository;

    public RegistrasiController() throws ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        userRepository = new UserRepository();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/views/register.jsp").forward(req,resp);
        System.out.println("Hello");
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String usernameReq = req.getParameter("username");
        String passwordReq = req.getParameter("password");

        UserModel user = new UserModel();
        user.setUsername(usernameReq);
        user.setPassword(passwordReq);

        userRepository.save(user);

        HttpSession session = req.getSession();
        session.setAttribute("succes_regis",user.getUsername()+ "succes di daftar");
        resp.sendRedirect(req.getContextPath()+"/register");
    }
}
