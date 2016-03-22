package com.tlongdev.spicio.storage.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Set;

/**
 * @author Long
 * @since 2016. 03. 15.
 */
@Document(collection = "users")
public class UserDocument {

    @Id private long id;

    private String name;

    private String email;

    private String facebookId;

    private String googleId;

    private Set<Long> friends;

    private Set<Integer> series;

    private List<ActionDocument> history;

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

    public Set<Integer> getSeries() {
        return series;
    }

    public void setSeries(Set<Integer> series) {
        this.series = series;
    }

    public List<ActionDocument> getHistory() {
        return history;
    }

    public void setHistory(List<ActionDocument> history) {
        this.history = history;
    }
}