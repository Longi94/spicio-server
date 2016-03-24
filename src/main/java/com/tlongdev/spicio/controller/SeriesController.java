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
    public ResponseEntity<?> addSeries(@PathVariable long userId, @Valid @RequestBody SeriesBody seriesBody) {
        SeriesDocument result = seriesDao.addSeries(userId, seriesBody);

        if (result != null) {
            return ResponseEntity.ok(null);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
