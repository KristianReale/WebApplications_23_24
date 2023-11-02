package it.unical.informatica.webapp24.recensioniristoranti.controller;

import it.unical.informatica.webapp24.recensioniristoranti.model.Ristorante;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class ServiziRest {
    @GetMapping("/hello")
    public String hello(){
        return "Hello";
    }

    @PostMapping("/addRistorante")
    public String aggiungiRistorante(ArrayList<Ristorante> ristoranti){
        //Salva ristoranti nel DB
        //Se è andato tutto bene
        for (Ristorante r : ristoranti) {
            System.out.println(r.getNome());
            System.out.println(r.getDescrizione());
            System.out.println(r.getUbicazione());
        }
        return "OK";
        //altrimenti
        // return "Errore: ....";
    }

}
