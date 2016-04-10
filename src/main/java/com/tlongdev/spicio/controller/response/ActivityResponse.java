package com.tlongdev.spicio.controller.response;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author longi
 * @since 2016.04.10.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ActivityResponse {

    public static final int WATCHED = 0;
    public static final int SKIPPED = 1;
    public static final int LIKED = 2;
    public static final int BECAME_FRIENDS = 3;

    private int type;

    private SeriesSimpleResponse series;

    private EpisodeSimpleResponse episode;

    private UserResponse culprit;

    private UserResponse victim;

    private long timestamp;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public SeriesSimpleResponse getSeries() {
        return series;
    }

    public void setSeries(SeriesSimpleResponse series) {
        this.series = series;
    }

    public EpisodeSimpleResponse getEpisode() {
        return episode;
    }

    public void setEpisode(EpisodeSimpleResponse episode) {
        this.episode = episode;
    }

    public UserResponse getCulprit() {
        return culprit;
    }

    public void setCulprit(UserResponse culprit) {
        this.culprit = culprit;
    }

    public UserResponse getVictim() {
        return victim;
    }

    public void setVictim(UserResponse victim) {
        this.victim = victim;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
