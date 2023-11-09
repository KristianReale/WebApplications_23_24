package it.unical.informatica.webapp24.recensioniristoranti.persistenza;

public class DBManager {
    private static DBManager instance = null;

    private DBManager(){}

    public static DBManager getInstance(){
        if (instance == null){
            instance = new DBManager();
        }
        return instance;
    }

    public Connection getConnection(){
        return null;
    }
}
