package com.tlongdev.spicio.storage.dao.impl;

import com.tlongdev.spicio.controller.request.EpisodeBody;
import com.tlongdev.spicio.controller.request.SeriesBody;
import com.tlongdev.spicio.controller.response.SeriesResponse;
import com.tlongdev.spicio.controller.response.UserEpisodesResponse;
import com.tlongdev.spicio.converter.EpisodeConverter;
import com.tlongdev.spicio.converter.SeriesConverter;
import com.tlongdev.spicio.exception.DocumentNotFoundException;
import com.tlongdev.spicio.storage.dao.SeriesDao;
import com.tlongdev.spicio.storage.document.EpisodeDocument;
import com.tlongdev.spicio.storage.document.SeriesDocument;
import com.tlongdev.spicio.storage.document.UserDocument;
import com.tlongdev.spicio.storage.document.UserSeriesDocument;
import com.tlongdev.spicio.storage.mongo.SeriesRepository;
import com.tlongdev.spicio.storage.mongo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
    public void addEpisode(int seriesId, EpisodeBody episodeBody) {

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

        UserSeriesDocument series = user.getSeries().get(seriesId);
        series.getSkipped().remove(body.getTraktId());
        series.getWatched().put(body.getTraktId(), body.getTimestamp());

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

        UserSeriesDocument series = user.getSeries().get(seriesId);
        series.getWatched().remove(body.getTraktId());
        series.getSkipped().put(body.getTraktId(), body.getTimestamp());

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

        user.getSeries().get(seriesId).getLiked().put(body.getTraktId(), body.getTimestamp());

        userRepository.save(user);
    }

    @Override
    public void unCheckEpisode(long userId, int seriesId, int episodeId) {
        //Find the user
        UserDocument user = userRepository.findUserById(userId);

        if (user == null) {
            //User doesn't exist
            throw new DocumentNotFoundException();
        }

        user.getSeries().get(seriesId).getWatched().remove(episodeId);

        userRepository.save(user);
    }

    @Override
    public void unSkipEpisode(long userId, int seriesId, int episodeId) {
        //Find the user
        UserDocument user = userRepository.findUserById(userId);

        if (user == null) {
            //User doesn't exist
            throw new DocumentNotFoundException();
        }

        user.getSeries().get(seriesId).getSkipped().remove(episodeId);

        userRepository.save(user);
    }

    @Override
    public void unLikeEpisode(long userId, int seriesId, int episodeId) {
        //Find the user
        UserDocument user = userRepository.findUserById(userId);

        if (user == null) {
            //User doesn't exist
            throw new DocumentNotFoundException();
        }

        user.getSeries().get(seriesId).getLiked().remove(episodeId);

        userRepository.save(user);
    }

    @Override
    public List<SeriesResponse> getSeries(long userId) {
        //Find the user
        UserDocument user = userRepository.findUserById(userId);

        if (user == null) {
            //User doesn't exist
            throw new DocumentNotFoundException();
        }

        List<SeriesResponse> response = new LinkedList<>();
        for (SeriesDocument seriesDoc : seriesRepository.findAll(user.getSeries().keySet())) {
            response.add(SeriesConverter.convertToSeriesResponse(seriesDoc));
        }

        return response;
    }

    @Override
    public UserEpisodesResponse getEpisodes(long userId, int seriesId) {
        //Find the user
        UserDocument user = userRepository.findUserById(userId);

        if (user == null || !user.getSeries().containsKey(seriesId)) {
            //User or series doesn't exist
            throw new DocumentNotFoundException();
        }

        return SeriesConverter.convertToUserSeriesResponse(user.getSeries().get(seriesId));
    }
}
