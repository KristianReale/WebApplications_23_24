package it.unical.informatica.webapp24.recensioniristoranti.persistenza.dao.postgres;

import it.unical.informatica.webapp24.recensioniristoranti.persistenza.DBManager;
import it.unical.informatica.webapp24.recensioniristoranti.persistenza.model.Piatto;
import it.unical.informatica.webapp24.recensioniristoranti.persistenza.model.Ristorante;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PiattoProxy extends Piatto {
    Connection conn;

    public PiattoProxy(Connection conn){
        this.conn = conn;
    }

    @Override
    public List<Ristorante> getRistoranti() {
        List<Ristorante> rists = new ArrayList<Ristorante>();
        if (super.getRistoranti() == null) {
            String query = "r.*  from " +
                    "piatto p, serve s, ristorante r " +
                    "where p.id = ? " +
                    "   s.piatto = p.id and s.ristorante = r.id";
            try {
                PreparedStatement st = conn.prepareStatement(query);
                st.setLong(1, getId());
                ResultSet rs = st.executeQuery();

                Ristorante r = new Ristorante();
                r.setId(rs.getLong("id"));
                r.setNome(rs.getString("nome"));
                r.setUbicazione(rs.getString("ubicazione"));
                r.setDescrizione(rs.getString("descrizione"));
                rists.add(r);


            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            super.setRistoranti(rists);
            return rists;
        }else{
            return super.getRistoranti();
        }

    }
}
