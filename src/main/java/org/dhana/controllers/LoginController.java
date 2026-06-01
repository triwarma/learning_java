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


@WebServlet("/login")
public class LoginController extends HttpServlet {
    private UserRepository userRepository;

    public LoginController() throws ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        this.userRepository = new UserRepository();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        System.out.println(session.getAttribute("error"));
        req.getRequestDispatcher("WEB-INF/views/login.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        String usernameReq = req.getParameter("username");
        String passwordReq = req.getParameter("password");

        UserModel user = userRepository.getUserByUsername(usernameReq);
        if(user == null || !user.getPassword().equals(passwordReq)){
            ;
            session.setAttribute("error", "Username or Password invalid");
            resp.sendRedirect(req.getContextPath()+ "/login");
            return;
        }
        session.setAttribute("has_login",user);
        resp.sendRedirect("/dashboard");
    }
}
