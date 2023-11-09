package it.unical.informatica.webapp24.recensioniristoranti.persistenza.dao;

import it.unical.informatica.webapp24.recensioniristoranti.persistenza.model.Piatto;
import it.unical.informatica.webapp24.recensioniristoranti.persistenza.model.Ristorante;

import java.util.List;

public interface RistoranteDao {
    public List<Ristorante> findAll();

    public Ristorante findByPrimaryKey(Integer id);

    public void saveOrUpdate(Ristorante ristorante);

    public void delete(Ristorante ristorante);
}
