package com.tlongdev.spicio.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tlongdev.spicio.storage.document.ActivityDocument;

import java.util.List;
import java.util.Set;

/**
 * @author longi
 * @since 2016.03.22.
 */
public class UserResponseFull {

    private long id;

    private String name;

    private String email;

    @JsonProperty("facebook_id")
    private String facebookId;

    @JsonProperty("google_id")
    private String googleId;

    // TODO: 2016.03.22. needs a better response structure
    private Set<Long> friends;

    private List<SeriesResponse> series;

    private List<ActivityDocument> history;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFacebookId() {
        return facebookId;
    }

    public void setFacebookId(String facebookId) {
        this.facebookId = facebookId;
    }

    public String getGoogleId() {
        return googleId;
    }

    public void setGoogleId(String googleId) {
        this.googleId = googleId;
    }

    public Set<Long> getFriends() {
        return friends;
    }

    public void setFriends(Set<Long> friends) {
        this.friends = friends;
    }

    public List<SeriesResponse> getSeries() {
        return series;
    }

    public void setSeries(List<SeriesResponse> series) {
        this.series = series;
    }

    public List<ActivityDocument> getHistory() {
        return history;
    }

    public void setHistory(List<ActivityDocument> history) {
        this.history = history;
    }
}
