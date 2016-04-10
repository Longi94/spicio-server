package com.tlongdev.spicio.converter;

import com.tlongdev.spicio.controller.request.EpisodeBody;
import com.tlongdev.spicio.controller.response.EpisodeSimpleResponse;
import com.tlongdev.spicio.storage.document.EpisodeDocument;

/**
 * @author longi
 * @since 2016.04.03.
 */
public class EpisodeConverter {

    public static EpisodeDocument convertToEpisodeDocument(EpisodeBody body) {
        EpisodeDocument document = new EpisodeDocument();
        document.setTraktId(body.getTraktId());
        document.setNumber(body.getNumber());
        document.setSeason(body.getSeason());
        document.setThumb(body.getThumb());
        document.setTitle(body.getTitle());
        return document;
    }

    public static EpisodeSimpleResponse convertToEpisodeSimpleResponse(EpisodeDocument document) {
        EpisodeSimpleResponse response = new EpisodeSimpleResponse();
        response.setNumber(document.getNumber());
        response.setTitle(document.getTitle());
        response.setThumb(document.getThumb());
        response.setSeason(document.getSeason());
        response.setTraktId(document.getTraktId());
        return response;
    }
}
