package it.unical.informatica.webapp24.recensioniristoranti.controller;

import it.unical.informatica.webapp24.recensioniristoranti.persistenza.model.Utente;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Base64;

@RestController
@CrossOrigin("http://localhost:4200")
public class Auth {
    private class AuthToken{
        String token;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
    @PostMapping("/login")
    public AuthToken login(@RequestBody Utente utente){
        String username = utente.getUsername();
        String password = utente.getPassword();
        String concat = username + ":" + password;
        String token = codificaBase64(concat);
        System.out.println(token);
        AuthToken auth = new AuthToken();
        auth.setToken(token);
        return auth;
    }

    public static String codificaBase64(String value){
        return Base64.getEncoder().encodeToString(value.getBytes());
    }

    public static String decodificaBase64(String value){
        return new String(Base64.getDecoder().decode(value.getBytes()));
    }
}
