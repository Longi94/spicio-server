package com.tlongdev.spicio.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author longi
 * @since 2016.03.30.
 */
public class SeriesResponse {

    private String title;

    private Integer year;

    @NotNull
    @JsonProperty("trakt_id")
    private Integer traktId;

    @JsonProperty("slug")
    private String slugName;

    @JsonProperty("tvdb_id")
    private Integer tvdbId;

    @JsonProperty("imdb_id")
    private String imdbId;

    @JsonProperty("tmdb_id")
    private Integer tmdbId;

    @JsonProperty("tv_rage_id")
    private Integer tvRageId;

    private String overview;

    @JsonProperty("first_aired")
    private Long firstAired;

    @JsonProperty("day_of_airing")
    private Integer dayOfAiring;

    @JsonProperty("time_of_airing")
    private String timeOfAiring;

    @JsonProperty("air_time_zone")
    private String airTimeZone;

    private Integer runtime;

    private String certification;

    private String network;

    private String trailer;

    private Integer status;

    @JsonProperty("trakt_rating")
    private Double traktRating;

    @JsonProperty("trakt_rating_count")
    private Integer traktRatingCount;

    private List<String> genres;

    @JsonProperty("poster_full")
    private String posterFull;

    @JsonProperty("poster_thumb")
    private String posterThumb;

    private String thumb;

    private UserEpisodesResponse userEpisodes;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getTraktId() {
        return traktId;
    }

    public void setTraktId(Integer traktId) {
        this.traktId = traktId;
    }

    public String getSlugName() {
        return slugName;
    }

    public void setSlugName(String slugName) {
        this.slugName = slugName;
    }

    public Integer getTvdbId() {
        return tvdbId;
    }

    public void setTvdbId(Integer tvdbId) {
        this.tvdbId = tvdbId;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public Integer getTmdbId() {
        return tmdbId;
    }

    public void setTmdbId(Integer tmdbId) {
        this.tmdbId = tmdbId;
    }

    public Integer getTvRageId() {
        return tvRageId;
    }

    public void setTvRageId(Integer tvRageId) {
        this.tvRageId = tvRageId;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public Long getFirstAired() {
        return firstAired;
    }

    public void setFirstAired(Long firstAired) {
        this.firstAired = firstAired;
    }

    public Integer getDayOfAiring() {
        return dayOfAiring;
    }

    public void setDayOfAiring(Integer dayOfAiring) {
        this.dayOfAiring = dayOfAiring;
    }

    public String getTimeOfAiring() {
        return timeOfAiring;
    }

    public void setTimeOfAiring(String timeOfAiring) {
        this.timeOfAiring = timeOfAiring;
    }

    public String getAirTimeZone() {
        return airTimeZone;
    }

    public void setAirTimeZone(String airTimeZone) {
        this.airTimeZone = airTimeZone;
    }

    public Integer getRuntime() {
        return runtime;
    }

    public void setRuntime(Integer runtime) {
        this.runtime = runtime;
    }

    public String getCertification() {
        return certification;
    }

    public void setCertification(String certification) {
        this.certification = certification;
    }

    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Double getTraktRating() {
        return traktRating;
    }

    public void setTraktRating(Double traktRating) {
        this.traktRating = traktRating;
    }

    public Integer getTraktRatingCount() {
        return traktRatingCount;
    }

    public void setTraktRatingCount(Integer traktRatingCount) {
        this.traktRatingCount = traktRatingCount;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public String getPosterFull() {
        return posterFull;
    }

    public void setPosterFull(String posterFull) {
        this.posterFull = posterFull;
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

    public void setUserEpisodes(UserEpisodesResponse userEpisodes) {
        this.userEpisodes = userEpisodes;
    }

    public UserEpisodesResponse getUserEpisodes() {
        return userEpisodes;
    }
}
