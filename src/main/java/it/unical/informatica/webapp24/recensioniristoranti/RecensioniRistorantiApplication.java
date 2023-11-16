package it.unical.informatica.webapp24.recensioniristoranti;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class RecensioniRistorantiApplication {

    public static void main(String[] args) {
        SpringApplication.run(RecensioniRistorantiApplication.class, args);
    }

}
