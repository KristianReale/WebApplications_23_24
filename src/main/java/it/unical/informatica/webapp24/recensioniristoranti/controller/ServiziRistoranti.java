package it.unical.informatica.webapp24.recensioniristoranti.controller;

import it.unical.informatica.webapp24.recensioniristoranti.persistenza.DBManager;
import it.unical.informatica.webapp24.recensioniristoranti.persistenza.model.Ristorante;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200/")
public class ServiziRistoranti {
    @GetMapping("/ristorantiMigliori")
    public List<Ristorante> dammiRistorantiMigliori(){
        List<Ristorante> migliori = DBManager.getInstance().getRistoranteDao().findAll();
        return migliori;
    }
}
