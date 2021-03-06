package com.tlongdev.spicio.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author longi
 * @since 2016.04.10.
 */
public class EpisodeSimpleResponse {

    @JsonProperty("trakt_id")
    private Integer traktId;

    private Integer number;

    private Integer season;

    private String title;

    private String thumb;

    public Integer getTraktId() {
        return traktId;
    }

    public void setTraktId(Integer traktId) {
        this.traktId = traktId;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getSeason() {
        return season;
    }

    public void setSeason(Integer season) {
        this.season = season;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }
}
