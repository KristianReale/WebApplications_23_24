package it.unical.informatica.webapp24.recensioniristoranti.persistenza.model;

import java.util.List;

public class Piatto {
    private Long id;
    private String nome;
    private Double prezzo;
    private List<Ristorante> ristoranti;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(Double prezzo) {
        this.prezzo = prezzo;
    }
}