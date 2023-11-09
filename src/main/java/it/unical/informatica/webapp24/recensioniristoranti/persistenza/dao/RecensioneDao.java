package it.unical.informatica.webapp24.recensioniristoranti.persistenza.dao;

import it.unical.informatica.webapp24.recensioniristoranti.persistenza.model.Recensione;

import java.util.List;

public interface RecensioneDao {
    public List<Recensione> findAll();

    public Recensione findByPrimaryKey(Long id);

    public void saveOrUpdate(Recensione recensione);

    public void delete(Recensione recensione);
}
