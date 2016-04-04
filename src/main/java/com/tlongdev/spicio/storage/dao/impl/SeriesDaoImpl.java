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
        boolean removed = user.getSeries().remove(seriesId) != null;

        //Update the user
        userRepository.save(user);

        return removed;
    }

    @Override
    public void addEpisode(long userId, int seriesId, EpisodeBody episodeBody) {

        //Check if series exists
        SeriesDocument seriesDoc = seriesRepository.findSeriesByTraktId(seriesId);

        if (seriesDoc == null) {
            //Series doesn't exist
            throw new DocumentNotFoundException();
        }

        //Add the episode to the series
        Map<Integer, EpisodeDocument> map = seriesDoc.getEpisodes();
        map.put(episodeBody.getTraktId(), EpisodeConverter.convertToEpisodeDocument(episodeBody));

        seriesRepository.save(seriesDoc);
    }

    @Override
    public void checkEpisode(long userId, int seriesId, EpisodeBody body) {
        //Find the user
        UserDocument user = userRepository.findUserById(userId);

        if (user == null) {
            //User doesn't exist
            throw new DocumentNotFoundException();
        }

        UserSeriesDocument series =  user.getSeries().get(seriesId);
        if (body.isWatched()) {
            series.getSkipped().remove(body.getTraktId());
            series.getWatched().put(body.getTraktId(), body.getTimestamp());
        } else {
            series.getWatched().remove(body.getTraktId());
        }

        userRepository.save(user);
    }

    @Override
    public void skipEpisode(long userId, int seriesId, EpisodeBody body) {

        //Find the user
        UserDocument user = userRepository.findUserById(userId);

        if (user == null) {
            //User doesn't exist
            throw new DocumentNotFoundException();
        }

        UserSeriesDocument series =  user.getSeries().get(seriesId);
        if (body.isSkipped()) {
            series.getWatched().remove(body.getTraktId());
            series.getSkipped().put(body.getTraktId(), body.getTimestamp());
        } else {
            series.getSkipped().remove(body.getTraktId());
        }

        userRepository.save(user);
    }

    @Override
    public void likeEpisode(long userId, int seriesId, EpisodeBody body) {
        //Find the user
        UserDocument user = userRepository.findUserById(userId);

        if (user == null) {
            //User doesn't exist
            throw new DocumentNotFoundException();
        }

        UserSeriesDocument series =  user.getSeries().get(seriesId);
        if (body.isLiked()) {
            series.getLiked().put(body.getTraktId(), body.getTimestamp());
        } else {
            series.getLiked().remove(body.getTraktId());
        }

        userRepository.save(user);
    }
}
