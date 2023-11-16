package it.unical.informatica.webapp24.recensioniristoranti.persistenza.dao.postgres;

import it.unical.informatica.webapp24.recensioniristoranti.persistenza.DBManager;
import it.unical.informatica.webapp24.recensioniristoranti.persistenza.IdBroker;
import it.unical.informatica.webapp24.recensioniristoranti.persistenza.dao.PiattoDao;
import it.unical.informatica.webapp24.recensioniristoranti.persistenza.dao.RistoranteDao;
import it.unical.informatica.webapp24.recensioniristoranti.persistenza.model.Piatto;
import it.unical.informatica.webapp24.recensioniristoranti.persistenza.model.Ristorante;


import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PiattoDaoPostgres implements PiattoDao {
    Connection connection;

    public PiattoDaoPostgres(Connection connection){
        this.connection = connection;
    }


    @Override
    public List<Piatto> findAllNonEfficiente() {
        List<Piatto> piattiLista = new ArrayList<Piatto>();
        try {
            Statement st = connection.createStatement();
            String query = "select * from piatto";

            ResultSet rs = st.executeQuery(query);
            while (rs.next()){
                Piatto piatto = findByPrimaryKey(rs.getLong("id"));
                piattiLista.add(piatto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return piattiLista;
    }

    public List<Piatto> findAllLazy() {
        List<Piatto> piattiLista = new ArrayList<Piatto>();
        try {
            Statement st = connection.createStatement();
            String query = "select * from piatto";

            ResultSet rs = st.executeQuery(query);
            while (rs.next()){
                Piatto piatto = new PiattoProxy(connection);
                piatto.setId(rs.getLong("id"));
                piatto.setNome(rs.getString("nome"));
                piatto.setPrezzo(rs.getDouble("prezzo"));
                piattiLista.add(piatto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return piattiLista;
    }
    @Override
    public List<Piatto> findAll() {
        List<Piatto> piattiLista = new ArrayList<Piatto>();
        try {
            Statement st = connection.createStatement();
            //String query = "select * from piatto";
            HashMap<Piatto, ArrayList<Ristorante>> piatti =
                        new HashMap<Piatto, ArrayList<Ristorante>>();

            String query = "select p.id as p_id, p.nome as p_nome, " +
                    "p.prezzo as p_prezzo, r.id as r_id  from " +
                    "piatto p, serve s, ristorante r " +
                    "where " +
                    "   s.piatto = p.id and s.ristorante = r.id";

            ResultSet rs = st.executeQuery(query);
            while (rs.next()){
                Long idPiatto = rs.getLong("p_id");

                Piatto piatto = new Piatto();
                piatto.setId(idPiatto);
                piatto.setNome(rs.getString("p_nome"));
                piatto.setPrezzo(rs.getDouble("p_prezzo"));

                ArrayList<Ristorante> ristoranti;
                if (!piatti.containsKey(piatto)){
                    ristoranti = new ArrayList<Ristorante>();
                    piatti.put(piatto, ristoranti);
                }else{
                    ristoranti = piatti.get(piatto);
                }

                Integer ristId = rs.getInt("r_id");
                Ristorante r = DBManager.getInstance().getRistoranteDao().findByPrimaryKey(ristId);
                ristoranti.add(r);
            }

            for (Piatto p : piatti.keySet()){
                p.setRistoranti(piatti.get(p));
                piattiLista.add(p);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return piattiLista;
    }

    @Override
    public Piatto findByPrimaryKey(Long id) {
        Piatto piatto = null;
        String query = "select p.id as p_id, p.nome as p_nome, " +
                                "p.prezzo as p_prezzo, r.id as r_id  from " +
                "piatto p, serve s, ristorante r " +
                "where p.id = ? " +
                "   and s.piatto = p.id and s.ristorante = r.id";
        try {
            PreparedStatement st = connection.prepareStatement(query);
            st.setLong(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()){
                if (piatto == null) {
                    piatto = new Piatto();
                    piatto.setId(rs.getLong("p_id"));
                    piatto.setNome(rs.getString("p_nome"));
                    piatto.setPrezzo(rs.getDouble("p_prezzo"));

                }



                Integer ristId = rs.getInt("r_id");
                Ristorante r = DBManager.getInstance().getRistoranteDao().findByPrimaryKey(ristId);
                piatto.addRistorante(r);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return piatto;
    }

    @Override
    public void saveOrUpdate(Piatto piatto) {
        if (piatto.getId() == null) {
            String insertStr = "INSERT INTO piatto VALUES (?, ?, ?)";

            try {
                PreparedStatement st = connection.prepareStatement(insertStr);

                Long newId = IdBroker.getId(connection);
                piatto.setId(newId);

                st.setLong(1, newId);
                st.setString(2, piatto.getNome());
                st.setDouble(3, piatto.getPrezzo());
                st.executeUpdate();

                RistoranteDao rDao = DBManager.getInstance().getRistoranteDao();
                for (Ristorante r : piatto.getRistoranti()) {
                    rDao.saveOrUpdate(r);

                    String serveStr = "INSERT INTO serve VALUES (?, ?, ?)";

                    PreparedStatement stServe = connection.prepareStatement(serveStr);

                    Long newIdServe = IdBroker.getId(connection);

                    stServe.setLong(1, newIdServe);
                    stServe.setLong(2, piatto.getId());
                    stServe.setLong(3, r.getId());

                    stServe.executeUpdate();
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }else {
            String updateStr = "UPDATE piatto set nome = ?, prezzo = ? "
                    + "where id = ?";

            PreparedStatement st;
            try {
                st = connection.prepareStatement(updateStr);

                st.setString(1, piatto.getNome());
                st.setDouble(2, piatto.getPrezzo());
                st.setLong(3, piatto.getId());

                st.executeUpdate();

                RistoranteDao rDao = DBManager.getInstance().getRistoranteDao();
                for (Ristorante r : piatto.getRistoranti()) {
                    rDao.saveOrUpdate(r);

                    String checkThereIs = "SELECT * FROM serve WHERE piatto = ? and ristorante = ?";
                    PreparedStatement stCheckServe = connection.prepareStatement(checkThereIs);
                    stCheckServe.setLong(1, piatto.getId());
                    stCheckServe.setLong(2, r.getId());

                    ResultSet rsCheck = stCheckServe.executeQuery();
                    PreparedStatement stServe;
                    if (rsCheck.next()) {
                        String serveStr = "UPDATE serve SET piatto = ?, ristorante = ? "
                                + "WHERE id = ?";
                        stServe = connection.prepareStatement(serveStr);

                        stServe.setLong(1, piatto.getId());
                        stServe.setLong(2, r.getId());
                        stServe.setLong(3, rsCheck.getLong("id"));
                    }else {
                        String serveStr = "INSERT INTO serve VALUES (?, ?, ?)";

                        stServe = connection.prepareStatement(serveStr);

                        Long newIdServe = IdBroker.getId(connection);

                        stServe.setLong(1, newIdServe);
                        stServe.setLong(2, piatto.getId());
                        stServe.setLong(3, r.getId());
                    }

                    stServe.executeUpdate();
                }



            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    @Override
    public void delete(Piatto piatto) {
        String query = "DELETE FROM piatto WHERE id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(query);
            st.setLong(1, piatto.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
