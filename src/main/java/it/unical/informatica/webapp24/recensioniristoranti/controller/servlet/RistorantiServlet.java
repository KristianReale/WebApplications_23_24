package it.unical.informatica.webapp24.recensioniristoranti.controller.servlet;

import it.unical.informatica.webapp24.recensioniristoranti.persistenza.DBManager;
import it.unical.informatica.webapp24.recensioniristoranti.persistenza.model.Ristorante;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/ristoranti/dammiRistoranti")
public class RistorantiServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Ristorante> ristoranti =
                DBManager.getInstance().getRistoranteDao().findAll();
        for (Ristorante r : ristoranti){
            System.out.println(r.getNome());
        }
        req.setAttribute("lista_ristoranti", ristoranti);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/views/ristoranti.html");
        dispatcher.forward(req, resp);
        //resp.getWriter().println("<h1>Funziona</h1>");
    }
}
