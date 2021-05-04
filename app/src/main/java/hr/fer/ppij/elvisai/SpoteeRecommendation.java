package hr.fer.ppij.elvisai;

import java.util.List;

public class SpoteeRecommendation {
    String track_id;
    String external_url;
    String album_name;
    String album_id;
    List<SpoteeArtist> artists;
    String cover_art_link;

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

    @Override
    public String toString() {
        return "SpoteeRecommendation{" +
                "track_id='" + track_id + '\'' +
                ", external_url='" + external_url + '\'' +
                ", album_name='" + album_name + '\'' +
                ", album_id='" + album_id + '\'' +
                ", artists=" + artists +
                ", cover_art_link='" + cover_art_link + '\'' +
                '}';
    }

    public String getCover_art_link() {
        return cover_art_link;
    }

    public void setCover_art_link(String cover_art_link) {
        this.cover_art_link = cover_art_link;
    }
}
