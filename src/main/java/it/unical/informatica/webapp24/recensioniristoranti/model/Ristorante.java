package it.unical.informatica.webapp24.recensioniristoranti.model;

public class Ristorante {
    String nome;
    String descrizione;
    String ubicazione;

    public Ristorante(String nome, String descrizione, String ubicazione){
        this.nome = nome;
        this.descrizione = descrizione;
        this.ubicazione = ubicazione;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getUbicazione() {
        return ubicazione;
    }

    public void setUbicazione(String ubicazione) {
        this.ubicazione = ubicazione;
    }

    public static void main(String[] args) {
        Ristorante r1 = new Ristorante("Nome", "Si mangia pizza", "Cosenza");
        r1.setNome("Girone dei golosi");
    }


}
