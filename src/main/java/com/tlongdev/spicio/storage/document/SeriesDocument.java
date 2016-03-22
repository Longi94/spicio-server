package com.tlongdev.spicio.storage.document;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * @author Long
 * @since 2016. 03. 19.
 */
@Document(collection = "")
public class SeriesDocument {

    private String title;

    private int year;

    @JsonProperty("trakt_id")
    @Id
    private int traktId;

    @JsonProperty("slug")
    private String slugName;

    @JsonProperty("tvdb_id")
    private int tvdbId;

    @JsonProperty("imdb_id")
    private String imdbId;

    @JsonProperty("tmdb_id")
    private int tmdbId;

    @JsonProperty("tv_rage_id")
    private int tvRageId;

    private String overview;

    @JsonProperty("first_aired")
    private long firstAired;

    @JsonProperty("day_of_airing")
    private int dayOfAiring;

    @JsonProperty("time_of_airing")
    private String timeOfAiring;

    @JsonProperty("air_time_zone")
    private String airTimeZone;

    private int runtime;

    private String certification;

    private String network;

    private String trailer;

    private int status;

    @JsonProperty("trakt_rating")
    private double traktRating;

    @JsonProperty("trakt_rating_count")
    private int traktRatingCount;

    private List<String> genres;

    @JsonProperty("poster_full")
    private String posterFull;

    @JsonProperty("poster_thumb")
    private String posterThumb;

    private String thumb;

    private List<EpisodeDocument> episodes;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getTraktId() {
        return traktId;
    }

    public void setTraktId(int traktId) {
        this.traktId = traktId;
    }

    public String getSlugName() {
        return slugName;
    }

    public void setSlugName(String slugName) {
        this.slugName = slugName;
    }

    public int getTvdbId() {
        return tvdbId;
    }

    public void setTvdbId(int tvdbId) {
        this.tvdbId = tvdbId;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public int getTmdbId() {
        return tmdbId;
    }

    public void setTmdbId(int tmdbId) {
        this.tmdbId = tmdbId;
    }

    public int getTvRageId() {
        return tvRageId;
    }

    public void setTvRageId(int tvRageId) {
        this.tvRageId = tvRageId;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public long getFirstAired() {
        return firstAired;
    }

    public void setFirstAired(long firstAired) {
        this.firstAired = firstAired;
    }

    public int getDayOfAiring() {
        return dayOfAiring;
    }

    public void setDayOfAiring(int dayOfAiring) {
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

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public double getTraktRating() {
        return traktRating;
    }

    public void setTraktRating(double traktRating) {
        this.traktRating = traktRating;
    }

    public int getTraktRatingCount() {
        return traktRatingCount;
    }

    public void setTraktRatingCount(int traktRatingCount) {
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
}
