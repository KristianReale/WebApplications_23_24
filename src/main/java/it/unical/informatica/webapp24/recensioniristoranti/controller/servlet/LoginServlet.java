package it.unical.informatica.webapp24.recensioniristoranti.controller.servlet;

import it.unical.informatica.webapp24.recensioniristoranti.persistenza.DBManager;
import it.unical.informatica.webapp24.recensioniristoranti.persistenza.model.Utente;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/doLogin")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        Utente utente = DBManager.getInstance().getUtenteDao().findByPrimaryKey(username);
        if (utente == null){
            System.out.println("Non sei autorizzato");
        }else{
            System.out.println("Utente " + utente.getUsername() + " trovato");
            if (password.equals(utente.getPassword())){
                System.out.println("La password corrisponde");
            }else{
                System.out.println("La password non corrisponde");
            }
        }
    }
}
