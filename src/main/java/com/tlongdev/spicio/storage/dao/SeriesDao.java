package com.tlongdev.spicio.storage.dao;

import com.tlongdev.spicio.controller.request.SeriesBody;
import com.tlongdev.spicio.exception.DocumentNotFoundException;
import com.tlongdev.spicio.storage.document.SeriesDocument;
import org.springframework.stereotype.Repository;

/**
 * @author longi
 * @since 2016.03.24.
 */
@Repository
public interface SeriesDao {
    SeriesDocument addSeries(long userId, SeriesBody series) throws DocumentNotFoundException;

    boolean removeSeries(long userId, int seriesId) throws DocumentNotFoundException;
}
