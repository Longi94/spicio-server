package com.tlongdev.spicio;

import com.tlongdev.spicio.storage.mongo.SequenceRepository;
import com.tlongdev.spicio.storage.mongo.SeriesRepository;
import com.tlongdev.spicio.storage.mongo.UserRepository;
import org.springframework.context.annotation.Bean;

/**
 * @author longi
 * @since 2016.03.27.
 */
public class SpicioTestConfiguration {

    @Bean
    public UserRepository userRepositoryTestBean() {
        return null;
    }

    @Bean
    public SeriesRepository seriesRepositoryTestBean() {
        return null;
    }

    @Bean
    public SequenceRepository sequenceRepositoryTestBean() {
        return null;
    }
}
