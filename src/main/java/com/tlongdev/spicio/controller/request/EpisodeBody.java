package com.tlongdev.spicio.controller.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

/**
 * @author longi
 * @since 2016.04.03.
 */
public class EpisodeBody {

    @JsonProperty("trakt_id")
    @NotNull
    private Integer traktId;

    private Integer number;

    private Integer season;

    private String title;

    private String thumb;

    @NotNull
    private Boolean watched;

    @NotNull
    private Boolean skipped;

    @NotNull
    private Boolean liked;

    public Integer getSeason() {
        return season;
    }

    public Integer getNumber() {
        return number;
    }

    public String getTitle() {
        return title;
    }

    public Integer getTraktId() {
        return traktId;
    }

    public Boolean isWatched() {
        return watched;
    }

    public Boolean isLiked() {
        return liked;
    }

    public Boolean isSkipped() {
        return skipped;
    }

    public String getThumb() {
        return thumb;
    }
}
