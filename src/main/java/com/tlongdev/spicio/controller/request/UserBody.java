package com.tlongdev.spicio.controller.request;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author longi
 * @since 2016.03.22.
 */
public class UserBody {

    private String name;

    private String email;

    @JsonProperty("facebook_id")
    private String facebookId;

    @JsonProperty("google_id")
    private String googleId;

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
}
