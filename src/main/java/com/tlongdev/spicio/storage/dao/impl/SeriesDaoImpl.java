package com.tlongdev.spicio.storage.dao.impl;

import com.tlongdev.spicio.controller.request.SeriesBody;
import com.tlongdev.spicio.exception.DocumentNotFoundException;
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
    public SeriesDocument addSeries(long userId, SeriesBody series) throws DocumentNotFoundException {
        //Find the user
        UserDocument user = userRepository.findUserById(userId);

        if (user == null) {
            //User doesn't exist
            throw new DocumentNotFoundException();
        }

        //Add the series to the user
        user.addSeries(series.getTraktId());
        userRepository.save(user);

        //Check if series already exists
        SeriesDocument seriesDoc = seriesRepository.findSeriesByTraktId(series.getTraktId());

        //Save the series to the database
        SeriesDocument newSeries = convertToSeriesDocument(series);
        if (seriesDoc == null) {
            return seriesRepository.insert(newSeries);
        } else {
            newSeries.setTraktId(seriesDoc.getTraktId());
            return seriesRepository.save(newSeries);
        }
    }

    @Override
    public boolean removeSeries(long userId, int seriesId) throws DocumentNotFoundException {
        //Find the user
        UserDocument user = userRepository.findUserById(userId);

        if (user == null) {
            //User doesn't exist
            throw new DocumentNotFoundException();
        }

        //Remove the series from the user
        boolean removed = user.getSeries().remove(seriesId);

        //Update the user
        userRepository.save(user);

        return removed;
    }
}
