package it.unical.informatica.webapp24.recensioniristoranti.persistenza.dao.postgres;

import it.unical.informatica.webapp24.recensioniristoranti.persistenza.dao.UtenteDao;
import it.unical.informatica.webapp24.recensioniristoranti.persistenza.model.Utente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;



public class UtenteDaoPostgres implements UtenteDao {
    Connection conn;

    public UtenteDaoPostgres(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Utente> findAll() {
        List<Utente> utenti = new ArrayList<Utente>();
        String query = "select * from utente";
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                Utente utente = new Utente();
                utente.setUsername(rs.getString("username"));
                utente.setPassword(rs.getString("password"));
                utente.setRuolo(rs.getString("ruolo"));
                utente.setNome(rs.getString("nome"));
                utente.setCognome(rs.getString("cognome"));

                long secs = rs.getDate("data_nascita").getTime();
                utente.setDataNascita(new java.util.Date(secs));

                utenti.add(utente);
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return utenti;
    }

    @Override
    public Utente findByPrimaryKey(String username) {
        Utente utente = null;
        String query = "select * from utente where username = ?";
        try {
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, username);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                utente = new Utente();
                utente.setUsername(rs.getString("username"));
                utente.setPassword(rs.getString("password"));
                utente.setRuolo(rs.getString("ruolo"));
                utente.setNome(rs.getString("nome"));
                utente.setCognome(rs.getString("cognome"));

                long secs = rs.getDate("data_nascita").getTime();
                utente.setDataNascita(new java.util.Date(secs));
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return utente;
    }

    @Override
    public void saveOrUpdate(Utente utente) {
        //Check existance of user
        if (findByPrimaryKey(utente.getUsername()) == null) {
            String insertStr = "INSERT INTO utente VALUES (?, ?, ?, ?, ?, ?)";

            PreparedStatement st;
            try {
                st = conn.prepareStatement(insertStr);
                st.setString(1, utente.getUsername());
                st.setString(2, utente.getPassword());
                st.setString(3, utente.getNome());
                st.setString(4, utente.getCognome());

                long secs = utente.getDataNascita().getTime();
                st.setDate(5, new java.sql.Date(secs));

                st.setString(6, utente.getRuolo());

                st.executeUpdate();

            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }else {
            String updateStr = "UPDATE utente set password = ?, "
                    + "nome = ?, "
                    + "cognome = ?, "
                    + "data_nascita = ?, "
                    + "ruolo = ? "
                    + "where username = ?";

            PreparedStatement st;
            try {
                st = conn.prepareStatement(updateStr);

                st.setString(1, utente.getPassword());
                st.setString(2, utente.getNome());
                st.setString(3, utente.getCognome());

                long secs = utente.getDataNascita().getTime();
                st.setDate(4, new java.sql.Date(secs));

                st.setString(5, utente.getRuolo());
                st.setString(6, utente.getUsername());

                st.executeUpdate();

            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
    }

    @Override
    public void delete(Utente utente) {
        String query = "DELETE FROM utente WHERE username = ?";
        try {
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, utente.getUsername());
            st.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
