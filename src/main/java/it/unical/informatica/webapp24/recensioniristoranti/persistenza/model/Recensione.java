package it.unical.informatica.webapp24.recensioniristoranti.persistenza.model;

public class Recensione {
    private Long id;
    private String titolo;
    private String testo;
    private Integer numeroMiPiace;
    private Integer numeroNonMiPiace;
    private Ristorante ristorante;
    private Utente scrittaDa;

    public Utente getScrittaDa() {
        return scrittaDa;
    }

    public void setScrittaDa(Utente scrittaDa) {
        this.scrittaDa = scrittaDa;
    }

    public Ristorante getRistorante() {
        return ristorante;
    }

    public void setRistorante(Ristorante ristorante) {
        this.ristorante = ristorante;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getTesto() {
        return testo;
    }

    public void setTesto(String testo) {
        this.testo = testo;
    }

    public Integer getNumeroMiPiace() {
        return numeroMiPiace;
    }

    public void setNumeroMiPiace(Integer numeroMiPiace) {
        this.numeroMiPiace = numeroMiPiace;
    }

    public Integer getNumeroNonMiPiace() {
        return numeroNonMiPiace;
    }

    public void setNumeroNonMiPiace(Integer numeroNonMiPiace) {
        this.numeroNonMiPiace = numeroNonMiPiace;
    }
}
