package com.tlongdev.spicio.storage.dao.impl;

import com.tlongdev.spicio.controller.request.EpisodeBody;
import com.tlongdev.spicio.controller.request.SeriesBody;
import com.tlongdev.spicio.converter.EpisodeConverter;
import com.tlongdev.spicio.exception.DocumentNotFoundException;
import com.tlongdev.spicio.storage.dao.SeriesDao;
import com.tlongdev.spicio.storage.document.*;
import com.tlongdev.spicio.storage.mongo.SeriesRepository;
import com.tlongdev.spicio.storage.mongo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

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
    public void addSeries(long userId, SeriesBody series) throws DocumentNotFoundException {
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
            seriesRepository.insert(newSeries);
        } else {
            newSeries.setTraktId(seriesDoc.getTraktId());
            seriesRepository.save(newSeries);
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

    @Override
    public void addEpisode(long userId, int seriesId, EpisodeBody episodeBody) {
        //Find the user
        UserDocument user = userRepository.findUserById(userId);

        if (user == null) {
            //User doesn't exist
            throw new DocumentNotFoundException();
        }

        //Check if series exists
        SeriesDocument seriesDoc = seriesRepository.findSeriesByTraktId(seriesId);

        if (seriesDoc == null) {
            //Series doesn't exist
            throw new DocumentNotFoundException();
        }

        //Add the episode to the series
        if (seriesDoc.getEpisodes() == null) {
            seriesDoc.setEpisodes(new HashMap<Integer, EpisodeDocument>());
        }
        Map<Integer, EpisodeDocument> map = seriesDoc.getEpisodes();
        map.put(episodeBody.getTraktId(), EpisodeConverter.convertToEpisodeDocument(episodeBody));

        if (user.getHistory() == null) {
            user.setHistory(new ArrayList<ActivityDocument>());
        }

        List<ActivityDocument> history = user.getHistory();

        seriesRepository.save(seriesDoc);
    }
}
