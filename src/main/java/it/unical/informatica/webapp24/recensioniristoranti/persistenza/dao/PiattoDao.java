package it.unical.informatica.webapp24.recensioniristoranti.persistenza.dao;

import it.unical.informatica.webapp24.recensioniristoranti.persistenza.model.Piatto;

import java.util.List;

public interface PiattoDao {
    public List<Piatto> findAll();

    public List<Piatto> findAllNonEfficiente();

    public List<Piatto> findAllLazy();


    public Piatto findByPrimaryKey(Long id);

    public void saveOrUpdate(Piatto piatto);

    public void delete(Piatto piatto);
}
