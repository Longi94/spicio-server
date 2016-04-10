package com.tlongdev.spicio.storage.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Long
 * @since 2016. 03. 15.
 */
@Document(collection = "users")
public class UserDocument {

    @Id private Long id;

    private String name;

    private String email;

    @Indexed
    private String facebookId;

    @Indexed
    private String googleId;

    @Indexed
    private String searchTerm;

    private Map<Long, Long> friends = new HashMap<>();

    private Map<Integer, UserSeriesDocument> series = new HashMap<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Map<Long, Long> getFriends() {
        return friends;
    }

    public void setFriends(Map<Long, Long> friends) {
        this.friends = friends;
    }

    public Map<Integer, UserSeriesDocument> getSeries() {
        return series;
    }

    public void setSeries(Map<Integer, UserSeriesDocument> series) {
        this.series = series;
    }

    public void buildSearchTerm() {
        if (name == null || email == null) {
            throw new IllegalStateException("Name and email cannot be null");
        }

        searchTerm  = name.toLowerCase() + ((char) 30) + email.toLowerCase(); //separate with Record Separator Ascii character
    }

    public String getSearchTerm() {
        return searchTerm;
    }

    public void addSeries(int traktId) {
        series.put(traktId, new UserSeriesDocument());
    }
}
