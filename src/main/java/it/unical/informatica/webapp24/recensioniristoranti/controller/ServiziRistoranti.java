package it.unical.informatica.webapp24.recensioniristoranti.controller;

import it.unical.informatica.webapp24.recensioniristoranti.persistenza.DBManager;
import it.unical.informatica.webapp24.recensioniristoranti.persistenza.model.Ristorante;
import it.unical.informatica.webapp24.recensioniristoranti.persistenza.model.Utente;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200/")
public class ServiziRistoranti {
    @GetMapping("/ristorantiMigliori")
    public List<Ristorante> dammiRistorantiMigliori(HttpServletRequest req){
        String auth = req.getHeader("Authorization");
        System.out.println(auth);
        String token = auth.substring("Basic ".length());
        System.out.println(token);
        //System.out.println(Auth.decodificaBase64(token));
        //String credenziali= Auth.decodificaBase64(token);
        //String [] creds = credenziali.split(":");
        //String username = creds[0];
        //String password = creds[1];
        //Utente utente = DBManager.getInstance().getUtenteDao().findByPrimaryKey(username);
        //if (utente != null){
        //    if (utente.getPassword().equals(password)){
                List<Ristorante> migliori = DBManager.getInstance().getRistoranteDao().findAll();
                return migliori;
        //    }
        //}
        //return null;
    }
}
