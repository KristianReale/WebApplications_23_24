package it.unical.informatica.webapp24.recensioniristoranti.persistenza.dao.postgres;

import it.unical.informatica.webapp24.recensioniristoranti.persistenza.DBManager;
import it.unical.informatica.webapp24.recensioniristoranti.persistenza.dao.RecensioneDao;
import it.unical.informatica.webapp24.recensioniristoranti.persistenza.model.Recensione;
import it.unical.informatica.webapp24.recensioniristoranti.persistenza.model.Ristorante;
import it.unical.informatica.webapp24.recensioniristoranti.persistenza.model.Utente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class RecensioneDaoPostgres implements RecensioneDao {
    Connection connection;

    public RecensioneDaoPostgres(Connection connection){
        this.connection = connection;
    }

    @Override
    public List<Recensione> findAll() {
        return null;
    }

    @Override
    public Recensione findByPrimaryKey(Long id) {
        Recensione rec = null;
        String query = "select * from recensione where id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(query);
            st.setLong(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()){
                rec = new Recensione();
                rec.setId(rs.getLong("id"));
                rec.setTitolo(rs.getString("titolo"));
                rec.setTesto(rs.getString("testo"));
                rec.setNumeroMiPiace(rs.getInt("numero_mipiace"));
                rec.setNumeroNonMiPiace(rs.getInt("numero_nonmipiace"));

                String username = rs.getString("scritta_da");
                Utente scrittaDa = DBManager.getInstance().getUtenteDao()
                                .findByPrimaryKey(username);
                rec.setScrittaDa(scrittaDa);

                Integer ristId = rs.getInt("ristorante");
                Ristorante ristorante = DBManager.getInstance().getRistoranteDao()
                        .findByPrimaryKey(ristId);
                rec.setRistorante(ristorante);


            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return rec;
    }

    @Override
    public void saveOrUpdate(Recensione piatto) {

    }

    @Override
    public void delete(Recensione piatto) {

    }
}
