package com.tlongdev.spicio.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

/**
 * @author longi
 * @since 2016.04.10.
 */
public class SeriesSimpleResponse {

    private String title;

    @NotNull
    @JsonProperty("trakt_id")
    private Integer traktId;

    @JsonProperty("poster_thumb")
    private String posterThumb;

    private String thumb;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getTraktId() {
        return traktId;
    }

    public void setTraktId(Integer traktId) {
        this.traktId = traktId;
    }

    public String getPosterThumb() {
        return posterThumb;
    }

    public void setPosterThumb(String posterThumb) {
        this.posterThumb = posterThumb;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }
}
