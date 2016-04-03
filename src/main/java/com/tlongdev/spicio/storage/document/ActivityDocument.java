package com.tlongdev.spicio.storage.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Long
 * @since 2016. 03. 19.
 */
@Document(collection = "activities")
public class ActivityDocument {

    @Id private Long id;

    private Long timeStamp;

    private ActivityType type;

    private Long culprit;

    private Long victim;

    private Integer series;

    private Integer episode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public ActivityType getType() {
        return type;
    }

    public void setType(ActivityType type) {
        this.type = type;
    }

    public Long getCulprit() {
        return culprit;
    }

    public void setCulprit(Long culprit) {
        this.culprit = culprit;
    }

    public Long getVictim() {
        return victim;
    }

    public void setVictim(Long victim) {
        this.victim = victim;
    }

    public Integer getSeries() {
        return series;
    }

    public void setSeries(Integer series) {
        this.series = series;
    }

    public Integer getEpisode() {
        return episode;
    }

    public void setEpisode(Integer episode) {
        this.episode = episode;
    }
}
