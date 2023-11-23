package it.unical.informatica.webapp24.recensioniristoranti.controller.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("")
public class HomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session != null){
            if (session.getAttribute("user") != null){
                req.setAttribute("user", session.getAttribute("user"));
            }
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher("views/index.html");
        dispatcher.forward(req, resp);
    }
}
