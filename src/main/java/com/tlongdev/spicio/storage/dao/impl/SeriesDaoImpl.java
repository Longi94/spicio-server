package com.tlongdev.spicio.storage.dao.impl;

import com.tlongdev.spicio.controller.request.SeriesBody;
import com.tlongdev.spicio.storage.dao.SeriesDao;
import com.tlongdev.spicio.storage.document.SeriesDocument;
import com.tlongdev.spicio.storage.document.UserDocument;
import com.tlongdev.spicio.storage.mongo.SeriesRepository;
import com.tlongdev.spicio.storage.mongo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import static com.tlongdev.spicio.converter.SeriesConverter.convertToSeriesDocument;

/**
 * @author longi
 * @since 2016.03.24.
 */
@Repository
public class SeriesDaoImpl implements SeriesDao {

    @Autowired private UserRepository userRepository;
    @Autowired private SeriesRepository seriesRepository;

    @Override
    public SeriesDocument addSeries(long userId, SeriesBody series) {
        UserDocument user = userRepository.findUserById(userId);

        if (user == null) {
            return null;
        }

        user.addSeries(series.getTraktId());
        userRepository.save(user);

        SeriesDocument document = convertToSeriesDocument(series);
        return seriesRepository.save(document);
    }

    @Override
    public boolean removeSeries(long userId, int seriesId) {
        UserDocument user = userRepository.findUserById(userId);

        if (user == null) {
            return false;
        }

        boolean removed = user.getSeries().remove(seriesId);
        userRepository.save(user);
        return removed;
    }
}