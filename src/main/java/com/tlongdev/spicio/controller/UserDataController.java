package com.tlongdev.spicio.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author longi
 * @since 2016.04.10.
 */
@RestController
@RequestMapping("/api/v1/users/{userId}")
public class UserDataController {

    @RequestMapping(value = "/discover", method = RequestMethod.GET)
    public ResponseEntity<Void> getRecommendations(@PathVariable long userId) {
        // TODO: 2016.04.10. implement me
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(null);
    }

    @RequestMapping(value = "/discover", method = RequestMethod.POST)
    public ResponseEntity<Void> dismissRecommendation(@PathVariable long userId, @RequestParam("series_id") int seriesId) {
        // TODO: 2016.04.10. implement me
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(null);
    }

    @RequestMapping(value = "/feed", method = RequestMethod.GET)
    public ResponseEntity<List<?>> getFeed(@PathVariable long userId) {
        // TODO: 2016.04.10. implement me
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(null);
    }

    @RequestMapping(value = "/history", method = RequestMethod.GET)
    public ResponseEntity<List<?>> getHistory(@PathVariable long userId) {
        // TODO: 2016.04.10. implement me
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(null);
    }
}
