package it.unical.informatica.webapp24.recensioniristoranti.persistenza;

import it.unical.informatica.webapp24.recensioniristoranti.persistenza.dao.PiattoDao;
import it.unical.informatica.webapp24.recensioniristoranti.persistenza.dao.RecensioneDao;
import it.unical.informatica.webapp24.recensioniristoranti.persistenza.dao.RistoranteDao;
import it.unical.informatica.webapp24.recensioniristoranti.persistenza.dao.UtenteDao;
import it.unical.informatica.webapp24.recensioniristoranti.persistenza.dao.postgres.PiattoDaoPostgres;
import it.unical.informatica.webapp24.recensioniristoranti.persistenza.dao.postgres.RecensioneDaoPostgres;
import it.unical.informatica.webapp24.recensioniristoranti.persistenza.dao.postgres.RistoranteDaoPostgres;
import it.unical.informatica.webapp24.recensioniristoranti.persistenza.dao.postgres.UtenteDaoPostgres;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBManager {
    private static DBManager instance = null;

    private DBManager(){}

    public static DBManager getInstance(){
        if (instance == null){
            instance = new DBManager();
        }
        return instance;
    }

    Connection con = null;

    public Connection getConnection(){
        if (con == null){
            try {
                con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/webapp24_ristoranti", "postgres", "postgres");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return con;
    }

    public PiattoDao getPiattoDao(){
        return new PiattoDaoPostgres(getConnection());
    }

    public UtenteDao getUtenteDao(){
        return new UtenteDaoPostgres(getConnection());
    }

    public RistoranteDao getRistoranteDao(){
        return new RistoranteDaoPostgres(getConnection());
    }


    public RecensioneDao getRecensioneDao(){
        return new RecensioneDaoPostgres(getConnection());
    }
    /*
    public static void main(String[] args) {
        Connection con = DBManager.getInstance().getConnection();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from utenti");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }*/
}
