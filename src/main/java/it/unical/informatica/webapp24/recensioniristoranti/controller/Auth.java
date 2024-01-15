package it.unical.informatica.webapp24.recensioniristoranti.controller;

import it.unical.informatica.webapp24.recensioniristoranti.persistenza.DBManager;
import it.unical.informatica.webapp24.recensioniristoranti.persistenza.model.Utente;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Base64;

@RestController
@CrossOrigin(value = "http://localhost:4200", allowCredentials = "true")
public class Auth {
    private class AuthToken{
        String token;
        Utente utente;

        public Utente getUtente() {
            return utente;
        }
        public void setUtente(Utente utente) {
            this.utente = utente;
        }
        public String getToken() {
            return token;
        }
        public void setToken(String token) {
            this.token = token;
        }
    }
    @PostMapping("/login")
    public AuthToken login(@RequestBody Utente utente, HttpServletRequest req) throws Exception{
        String username = utente.getUsername();
        String password = utente.getPassword();
        String concat = username + ":" + password;
        String token = codificaBase64(concat);
        utente = getUserByToken(token);
        if (utente != null){
            HttpSession session = req.getSession();
            session.setAttribute("user", utente);
            AuthToken auth = new AuthToken();
            auth.setToken(token);
            auth.setUtente(utente);
            return auth;
        }
        return null;
    }

    @PostMapping("/logout")
    public boolean logout(@RequestBody Utente utente, HttpServletRequest req) throws Exception{
        return true;
    }

    @PostMapping("/isAuthenticated")
    public boolean isAuthenticated(HttpServletRequest req){
        String auth = req.getHeader("Authorization");
        if (auth != null){
            String token = auth.substring("Basic ".length());
            return getUserByToken(token) != null;
        }else{
            return false;
        }
    }

    public static Utente getUserByToken(String token){
        if (token != null) {
            String decod = decodificaBase64(token);
            String username = decod.split(":")[0];
            String password = decod.split(":")[1];
            Utente utente = DBManager.getInstance().getUtenteDao().findByPrimaryKey(username);
            if (utente != null) {
                if (utente.getPassword().equals(password)) {
                    return utente;
                }
            }
        }
        return null;
    }

    private static String codificaBase64(String value){
        return Base64.getEncoder().encodeToString(value.getBytes());
    }

    private static String decodificaBase64(String value){
        return new String(Base64.getDecoder().decode(value.getBytes()));
    }
}
