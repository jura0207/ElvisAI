package hr.fer.ppij.elvisai;

import java.util.List;

public class SpoteeRecommendation {
    String track_id;
    String external_url;
    String album_name;
    String album_id;
    String name;
    String release_date;
    List<SpoteeArtist> artists;
    String cover_art_link;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }


    public String getTrack_id() {
        return track_id;
    }

    public void setTrack_id(String track_id) {
        this.track_id = track_id;
    }

    public String getExternal_url() {
        return external_url;
    }

    public void setExternal_url(String external_url) {
        this.external_url = external_url;
    }

    public String getAlbum_name() {
        return album_name;
    }

    public void setAlbum_name(String album_name) {
        this.album_name = album_name;
    }

    public String getAlbum_id() {
        return album_id;
    }

    public void setAlbum_id(String album_id) {
        this.album_id = album_id;
    }

    public List<SpoteeArtist> getArtists() {
        return artists;
    }

    public void setArtists(List<SpoteeArtist> artists) {
        this.artists = artists;
    }

    public String getCover_art_link() {
        return cover_art_link;
    }

    public void setCover_art_link(String cover_art_link) {
        this.cover_art_link = cover_art_link;
    }

    @Override
    public String toString() {
        return "SpoteeRecommendation{" +
                "track_id='" + track_id + '\'' +
                ", external_url='" + external_url + '\'' +
                ", album_name='" + album_name + '\'' +
                ", album_id='" + album_id + '\'' +
                ", name='" + name + '\'' +
                ", release_date='" + release_date + '\'' +
                ", artists=" + artists +
                ", cover_art_link='" + cover_art_link + '\'' +
                '}';
    }

}