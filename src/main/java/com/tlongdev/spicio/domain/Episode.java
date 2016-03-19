package com.tlongdev.spicio.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;

/**
 * @author Long
 * @since 2016. 03. 19.
 */
public class Episode {

    @JsonProperty("trakt_id")
    @Id
    private int traktId;

    private int number;

    private int season;

    private String title;

    private String thumb;

    public int getTraktId() {
        return traktId;
    }

    public void setTraktId(int traktId) {
        this.traktId = traktId;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getSeason() {
        return season;
    }

    public void setSeason(int season) {
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
