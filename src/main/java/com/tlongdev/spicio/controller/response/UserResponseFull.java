package com.tlongdev.spicio.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * @author longi
 * @since 2016.03.22.
 */
public class UserResponseFull extends UserResponse {

    @JsonProperty("facebook_id")
    private String facebookId;

    @JsonProperty("google_id")
    private String googleId;

    private List<UserResponse> friends;

    private List<SeriesResponse> series;

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

    public List<UserResponse> getFriends() {
        return friends;
    }

    public void setFriends(List<UserResponse> friends) {
        this.friends = friends;
    }

    public List<SeriesResponse> getSeries() {
        return series;
    }

    public void setSeries(List<SeriesResponse> series) {
        this.series = series;
    }
}
