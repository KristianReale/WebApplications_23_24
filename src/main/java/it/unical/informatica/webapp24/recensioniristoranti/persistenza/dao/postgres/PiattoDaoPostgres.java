package it.unical.informatica.webapp24.recensioniristoranti.persistenza.dao.postgres;

import it.unical.informatica.webapp24.recensioniristoranti.persistenza.dao.PiattoDao;
import it.unical.informatica.webapp24.recensioniristoranti.persistenza.model.Piatto;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PiattoDaoPostgres implements PiattoDao {
    Connection connection;

    public PiattoDaoPostgres(Connection connection){
        this.connection = connection;
    }

    @Override
    public List<Piatto> findAll() {
        List<Piatto> piatti = new ArrayList<Piatto>();
        try {
            Statement st = connection.createStatement();
            String query = "select * from piatto";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()){
                Piatto piatto = new Piatto();
                piatto.setId(rs.getLong("id"));
                piatto.setNome(rs.getString("nome"));
                piatto.setPrezzo(rs.getDouble("prezzo"));
                piatti.add(piatto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return piatti;
    }

    @Override
    public Piatto findByPrimaryKey(Long id) {
        return null;
    }

    @Override
    public void saveOrUpdate(Piatto piatto) {

    }

    @Override
    public void delete(Piatto piatto) {

    }
}
