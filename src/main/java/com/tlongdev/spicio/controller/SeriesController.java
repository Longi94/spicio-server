package com.tlongdev.spicio.controller;

import com.tlongdev.spicio.controller.request.SeriesBody;
import com.tlongdev.spicio.storage.dao.SeriesDao;
import com.tlongdev.spicio.storage.document.SeriesDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author longi
 * @since 2016.03.24.
 */
@RestController
@RequestMapping("/api/v1/users/{userId}/series")
public class SeriesController {

    @Autowired private SeriesDao seriesDao;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> addSeries(@PathVariable long userId, @Valid @RequestBody SeriesBody seriesBody) {
        SeriesDocument result = seriesDao.addSeries(userId, seriesBody);
        return ResponseEntity.ok(null);
    }

    @RequestMapping(value = "/{seriesId}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteSeries(@PathVariable long userId, @PathVariable int seriesId) {
        seriesDao.removeSeries(userId, seriesId);
        return ResponseEntity.ok(null);
    }
}
