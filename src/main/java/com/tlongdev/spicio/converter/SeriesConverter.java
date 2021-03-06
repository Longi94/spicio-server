package com.tlongdev.spicio.converter;

import com.tlongdev.spicio.controller.request.SeriesBody;
import com.tlongdev.spicio.controller.response.SeriesResponse;
import com.tlongdev.spicio.controller.response.SeriesSimpleResponse;
import com.tlongdev.spicio.controller.response.UserEpisodesResponse;
import com.tlongdev.spicio.storage.document.SeriesDocument;
import com.tlongdev.spicio.storage.document.UserSeriesDocument;

import java.util.Map;

/**
 * @author longi
 * @since 2016.03.24.
 */
public class SeriesConverter {
    public static SeriesDocument convertToSeriesDocument(SeriesBody seriesBody) {
        SeriesDocument document = new SeriesDocument();
        document.setAirTimeZone(seriesBody.getAirTimeZone());
        document.setCertification(seriesBody.getCertification());
        document.setDayOfAiring(seriesBody.getDayOfAiring());
        document.setFirstAired(seriesBody.getFirstAired());
        document.setGenres(seriesBody.getGenres());
        document.setImdbId(seriesBody.getImdbId());
        document.setNetwork(seriesBody.getNetwork());
        document.setOverview(seriesBody.getOverview());
        document.setPosterFull(seriesBody.getPosterFull());
        document.setPosterThumb(seriesBody.getPosterThumb());
        document.setRuntime(seriesBody.getRuntime());
        document.setSlugName(seriesBody.getSlugName());
        document.setStatus(seriesBody.getStatus());
        document.setThumb(seriesBody.getThumb());
        document.setTimeOfAiring(seriesBody.getTimeOfAiring());
        document.setTitle(seriesBody.getTitle());
        document.setTmdbId(seriesBody.getTmdbId());
        document.setTrailer(seriesBody.getTrailer());
        document.setTraktId(seriesBody.getTraktId());
        document.setTraktRating(seriesBody.getTraktRating());
        document.setTraktRatingCount(seriesBody.getTraktRatingCount());
        document.setTvdbId(seriesBody.getTvdbId());
        document.setTvRageId(seriesBody.getTvRageId());
        document.setYear(seriesBody.getYear());
        return document;
    }

    public static SeriesResponse convertToSeriesResponse(SeriesDocument seriesDocument) {
        SeriesResponse response = new SeriesResponse();
        response.setAirTimeZone(seriesDocument.getAirTimeZone());
        response.setCertification(seriesDocument.getCertification());
        response.setDayOfAiring(seriesDocument.getDayOfAiring());
        response.setFirstAired(seriesDocument.getFirstAired());
        response.setGenres(seriesDocument.getGenres());
        response.setImdbId(seriesDocument.getImdbId());
        response.setNetwork(seriesDocument.getNetwork());
        response.setOverview(seriesDocument.getOverview());
        response.setPosterFull(seriesDocument.getPosterFull());
        response.setPosterThumb(seriesDocument.getPosterThumb());
        response.setRuntime(seriesDocument.getRuntime());
        response.setSlugName(seriesDocument.getSlugName());
        response.setStatus(seriesDocument.getStatus());
        response.setThumb(seriesDocument.getThumb());
        response.setTimeOfAiring(seriesDocument.getTimeOfAiring());
        response.setTitle(seriesDocument.getTitle());
        response.setTmdbId(seriesDocument.getTmdbId());
        response.setTrailer(seriesDocument.getTrailer());
        response.setTraktId(seriesDocument.getTraktId());
        response.setTraktRating(seriesDocument.getTraktRating());
        response.setTraktRatingCount(seriesDocument.getTraktRatingCount());
        response.setTvdbId(seriesDocument.getTvdbId());
        response.setTvRageId(seriesDocument.getTvRageId());
        response.setYear(seriesDocument.getYear());
        return response;

    }

    public static UserEpisodesResponse convertToUserSeriesResponse(UserSeriesDocument document) {
        UserEpisodesResponse response = new UserEpisodesResponse();

        for (Map.Entry<Integer, Long> entry : document.getWatched().entrySet()) {
            response.getWatched().put(entry.getKey(), entry.getValue());
        }

        for (Map.Entry<Integer, Long> entry : document.getSkipped().entrySet()) {
            response.getSkipped().put(entry.getKey(), entry.getValue());
        }

        for (Map.Entry<Integer, Long> entry : document.getLiked().entrySet()) {
            response.getLiked().put(entry.getKey(), entry.getValue());
        }

        return response;
    }

    public static SeriesSimpleResponse convertToSeriesSimpleResponse(SeriesDocument seriesDoc) {
        SeriesSimpleResponse response = new SeriesSimpleResponse();
        response.setTraktId(seriesDoc.getTraktId());
        response.setThumb(seriesDoc.getThumb());
        response.setTitle(seriesDoc.getTitle());
        response.setPosterThumb(seriesDoc.getPosterThumb());
        return response;
    }
}
