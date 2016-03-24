package com.tlongdev.spicio.converter;

import com.tlongdev.spicio.controller.request.SeriesBody;
import com.tlongdev.spicio.storage.document.SeriesDocument;

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
}
