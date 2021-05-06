package hr.fer.ppij.elvisai;

public class SpoteeArtist {
    String artist_id;
    String artist_name;

    public String getArtist_id() {
        return artist_id;
    }

    public void setArtist_id(String artist_id) {
        this.artist_id = artist_id;
    }

    public String getArtist_name() {
        return artist_name;
    }

    public void setArtist_name(String artist_name) {
        this.artist_name = artist_name;
    }

    @Override
    public String toString() {
        return "SpoteeArtist{" +
                "artist_id='" + artist_id + '\'' +
                ", artist_name='" + artist_name + '\'' +
                '}';
    }
}
