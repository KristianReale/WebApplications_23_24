package it.unical.informatica.webapp24.recensioniristoranti.controller;

import it.unical.informatica.webapp24.recensioniristoranti.persistenza.DBManager;
import it.unical.informatica.webapp24.recensioniristoranti.persistenza.dao.PiattoDao;
import it.unical.informatica.webapp24.recensioniristoranti.persistenza.model.Piatto;
import it.unical.informatica.webapp24.recensioniristoranti.persistenza.model.Ristorante;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ServiziRest {
    @GetMapping("/hello")
    public String hello(){
        return "Hello";
    }

    @PostMapping("/ristoranti/addRistorante")
    public String aggiungiRistorante(@RequestBody ArrayList<Ristorante> ristoranti){
        //Salva ristoranti nel DB
        //Se è andato tutto bene
        System.out.println("Stampa");
        System.out.println(ristoranti);
        for (Ristorante r : ristoranti) {
            System.out.println(r.getNome());
            System.out.println(r.getDescrizione());
            System.out.println(r.getUbicazione());
        }
        return "OK";
        //altrimenti
        // return "Errore: ....";
    }

    @GetMapping("/dammiPiatti")
    public List<Piatto> getPiatti(){
        PiattoDao dao = DBManager.getInstance().getPiattoDao();
        List<Piatto> piatti = dao.findAllLazy();
        Piatto primoPiatto = piatti.get(0);
        System.out.println(primoPiatto.getRistoranti().get(0).getNome());
        return piatti;
    }

}
