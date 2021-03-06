package com.tlongdev.spicio.controller;

import com.tlongdev.spicio.controller.request.EpisodeBody;
import com.tlongdev.spicio.controller.request.SeriesBody;
import com.tlongdev.spicio.controller.response.SeriesResponse;
import com.tlongdev.spicio.controller.response.UserEpisodesResponse;
import com.tlongdev.spicio.storage.dao.SeriesDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author longi
 * @since 2016.03.24.
 */
@RestController
@RequestMapping("/api/v1/users/{userId}/series")
public class SeriesController {

    @Autowired private SeriesDao seriesDao;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<SeriesResponse>> getSeries(@PathVariable long userId) {
        return ResponseEntity.ok(seriesDao.getSeries(userId));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> addSeries(@PathVariable long userId, @Valid @RequestBody SeriesBody seriesBody) {
        seriesDao.addSeries(userId, seriesBody);
        return ResponseEntity.ok(null);
    }

    @RequestMapping(value = "/{seriesId}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteSeries(@PathVariable long userId, @PathVariable int seriesId) {
        seriesDao.removeSeries(userId, seriesId);
        return ResponseEntity.ok(null);
    }

    @RequestMapping(value = "/{seriesId}/episodes", method = RequestMethod.GET)
    public ResponseEntity<UserEpisodesResponse> getEpisodes(@PathVariable long userId, @PathVariable int seriesId) {
        UserEpisodesResponse response = seriesDao.getEpisodes(userId, seriesId);
        return ResponseEntity.ok(response);
    }

    @RequestMapping(value = "/{seriesId}/episodes/checks", method = RequestMethod.POST)
    public ResponseEntity<Void> checkEpisode(@PathVariable long userId, @PathVariable int seriesId,
                                             @RequestBody @Valid EpisodeBody episodeBody) {
        seriesDao.addEpisode(seriesId, episodeBody);
        seriesDao.checkEpisode(userId, seriesId, episodeBody);
        return ResponseEntity.ok(null);
    }

    @RequestMapping(value = "/{seriesId}/episodes/checks/{episodeId}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> unCheckEpisode(@PathVariable long userId, @PathVariable int seriesId,
                                               @PathVariable int episodeId) {
        seriesDao.unCheckEpisode(userId, seriesId, episodeId);
        return ResponseEntity.ok(null);
    }

    @RequestMapping(value = "/{seriesId}/episodes/skips", method = RequestMethod.POST)
    public ResponseEntity<Void> skipEpisode(@PathVariable long userId, @PathVariable int seriesId,
                                             @RequestBody @Valid EpisodeBody episodeBody) {
        seriesDao.addEpisode(seriesId, episodeBody);
        seriesDao.skipEpisode(userId, seriesId, episodeBody);
        return ResponseEntity.ok(null);
    }

    @RequestMapping(value = "/{seriesId}/episodes/skips/{episodeId}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> unSkipEpisode(@PathVariable long userId, @PathVariable int seriesId,
                                               @PathVariable int episodeId) {
        seriesDao.unSkipEpisode(userId, seriesId, episodeId);
        return ResponseEntity.ok(null);
    }

    @RequestMapping(value = "/{seriesId}/episodes/likes", method = RequestMethod.POST)
    public ResponseEntity<Void> likeEpisode(@PathVariable long userId, @PathVariable int seriesId,
                                             @RequestBody @Valid EpisodeBody episodeBody) {
        seriesDao.addEpisode(seriesId, episodeBody);
        seriesDao.likeEpisode(userId, seriesId, episodeBody);
        return ResponseEntity.ok(null);
    }

    @RequestMapping(value = "/{seriesId}/episodes/likes/{episodeId}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> unLikeEpisode(@PathVariable long userId, @PathVariable int seriesId,
                                              @PathVariable int episodeId) {
        seriesDao.unLikeEpisode(userId, seriesId, episodeId);
        return ResponseEntity.ok(null);
    }
}
