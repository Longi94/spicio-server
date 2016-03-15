package com.tlongdev.spicio;

import com.tlongdev.spicio.user.UserDao;
import com.tlongdev.spicio.user.UserDaoImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpicioServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpicioServerApplication.class, args);
    }

    @Bean
    UserDao userDaoBean() {
        return new UserDaoImpl();
    }
}
