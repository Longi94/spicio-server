package com.tlongdev.spicio.converter;

import com.tlongdev.spicio.controller.request.EpisodeBody;
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
}
