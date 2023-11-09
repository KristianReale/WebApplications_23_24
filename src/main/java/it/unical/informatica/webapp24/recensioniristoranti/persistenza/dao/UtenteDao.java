package it.unical.informatica.webapp24.recensioniristoranti.persistenza.dao;

import it.unical.informatica.webapp24.recensioniristoranti.persistenza.model.Recensione;
import it.unical.informatica.webapp24.recensioniristoranti.persistenza.model.Utente;

import java.util.List;

public interface UtenteDao {
    public List<Utente> findAll();

    public Utente findByPrimaryKey(String username);

    public void saveOrUpdate(Utente utente);

    public void delete(Utente utente);
}
