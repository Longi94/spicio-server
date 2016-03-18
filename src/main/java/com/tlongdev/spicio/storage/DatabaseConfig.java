package com.tlongdev.spicio.storage;

import com.tlongdev.spicio.storage.dao.UserDao;
import com.tlongdev.spicio.storage.dao.UserDaoImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * @author Long
 * @since 2016. 03. 18.
 */
@Configuration
public class DatabaseConfig {

    @Bean
    @Scope("singleton")
    UserDao userDaoBean() {
        return new UserDaoImpl();
    }
}
