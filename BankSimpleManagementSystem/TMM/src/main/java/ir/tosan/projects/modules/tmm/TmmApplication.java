package ir.tosan.projects.modules.tmm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TmmApplication {

    public static void main(String[] args) {
        SpringApplication.run(TmmApplication.class, args);
        System.out.println("Tosan Final Project: Transaction Management Module");
    }

}
