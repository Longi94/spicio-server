package com.tlongdev.spicio.storage.dao;

import com.tlongdev.spicio.controller.request.SeriesBody;
import com.tlongdev.spicio.storage.document.SeriesDocument;

/**
 * @author longi
 * @since 2016.03.24.
 */
public interface SeriesDao {
    SeriesDocument addSeries(long userId, SeriesBody series);

    boolean removeSeries(long userId, int seriesId);
}
