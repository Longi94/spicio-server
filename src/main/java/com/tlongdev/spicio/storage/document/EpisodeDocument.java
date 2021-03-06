package com.tlongdev.spicio.storage.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Long
 * @since 2016. 03. 19.
 */
@Document(collection = "episodes")
public class EpisodeDocument {

    @Id private Integer traktId;

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
