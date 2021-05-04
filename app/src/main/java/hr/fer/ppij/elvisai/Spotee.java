package hr.fer.ppij.elvisai;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/*
* Basic wrapper for a few Spotify API functionalities.
*
* */
public class Spotee {
    private Credentials credentials;
    private String access_token = null;
    private Object genres= null;
    final String URL_COMMA = "%2C";

    public Spotee() throws IOException {
        // Worst way to do this, fix if there's time. FIXME:
        this.credentials = new Credentials("8acd2e8856be4593a3abaf82e22cf2c9","f69be1fe167f4f0ab6e7bb0feff70c34");
        this.requestToken();
        this.getAvailableGenreSeeds();

    }

    public void requestToken() throws IOException {
        //api call to obtain OAuth token usin clientID and secret
        String endpoint = "https://accounts.spotify.com/api/token";
        URL url = new URL(endpoint);
        HttpURLConnection con = (HttpURLConnection)url.openConnection();
        con.setRequestMethod("POST");
        //setting header
        con.setRequestProperty("Authorization", "Basic " + this.credentials.getEncodedCredentials());

        //setting request body parameters
        con.setDoOutput(true);
        String urlPostParameters = "grant_type=client_credentials";
        DataOutputStream outputStream = new DataOutputStream(con.getOutputStream());

        outputStream.writeBytes(urlPostParameters);
        outputStream.flush();
        outputStream.close();

        if (con.getResponseCode() == HttpURLConnection.HTTP_OK){
            BufferedReader inputReader = new BufferedReader( new InputStreamReader(con.getInputStream()));
            StringBuffer response = new StringBuffer();
            String inputLine;
            while ((inputLine = inputReader.readLine()) != null) { response.append(inputLine); }
            Map<String, Object> map = new Gson().fromJson(response.toString(), Map.class);
            this.access_token = (String) map.get("access_token");
        }
        con.disconnect();
    }

    private String applyQuery(String baseURL, Map<String,Object> params){
        // creates GET query by combining baseURL and list of parameters
        boolean first = true;
        StringBuilder output = new StringBuilder(baseURL + '?');
        for (String key : params.keySet()){
            if (!first) output.append('&');
            Object value = params.get(key);
            output.append(key).append('=');
            if (value instanceof Integer){
                output.append(value.toString());
            } else if (value instanceof String[]){
                StringBuilder _temp = new StringBuilder();
                boolean _first = true;
                for (String i : (String[]) value){
                    if (!_first) _temp.append(URL_COMMA);
                    _temp.append(i);
                    _first = false;
                }
                output.append(_temp.toString());
            } else {
                output.append(value);
            }
            first = false;
        }
        return(output.toString());
    }

    public void getAvailableGenreSeeds() throws IOException {
        //Gets all available Genre seeds
        String endpoint = "https://api.spotify.com/v1/recommendations/available-genre-seeds";
        URL url = new URL(endpoint);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        //header
        this.requestToken();
        if (this.access_token != null){
            con.setRequestProperty("Authorization", "Bearer " + this.access_token);
        } else {
            throw new Error("Access token not obtained!");
        }

        if (con.getResponseCode() == HttpURLConnection.HTTP_OK){
            BufferedReader inputReader = new BufferedReader( new InputStreamReader(con.getInputStream()));
            StringBuffer response = new StringBuffer();
            String inputLine;
            while ((inputLine = inputReader.readLine()) != null) { response.append(inputLine); }
            Map<String, String[]> map = new Gson().fromJson(response.toString(), Map.class);
            this.genres = (Object) map.get("genres");
        }
        con.disconnect();
    }

    public ArrayList<SpoteeRecommendation> getRecommendations(Map<String,Object> params) throws IOException {
        //Object in argument map can only be String, String[] or Integer
        String endpoint = "https://api.spotify.com/v1/recommendations";
        String query = applyQuery(endpoint,params);
        URL url = new URL(query);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        this.requestToken(); //update token

        if (this.access_token != null){
            con.setRequestProperty("Authorization", "Bearer " + this.access_token);
        } else {
            throw new Error("Access token not obtained!");
        }

        ArrayList<SpoteeRecommendation> allRecommendations = new ArrayList<>();

        if (con.getResponseCode() == HttpURLConnection.HTTP_OK){
            BufferedReader inputReader = new BufferedReader( new InputStreamReader(con.getInputStream()));
            StringBuffer response = new StringBuffer();
            String inputLine;
            while ((inputLine = inputReader.readLine()) != null) { response.append(inputLine); }
            JsonObject output = new Gson().fromJson(response.toString(), JsonObject.class);
            for (JsonElement i : output.get("tracks").getAsJsonArray()){

                SpoteeRecommendation recommended_track = new SpoteeRecommendation();

                recommended_track.setAlbum_name(i.getAsJsonObject().get("album").getAsJsonObject().get("name").toString().replace("\"",""));
                recommended_track.setAlbum_id(i.getAsJsonObject().get("album").getAsJsonObject().get("id").toString().replace("\"",""));
                recommended_track.setCover_art_link(i.getAsJsonObject().get("album").getAsJsonObject().get("images").getAsJsonArray()
                        .get(0).getAsJsonObject().get("url").toString().replace("\"",""));
                recommended_track.setTrack_id(i.getAsJsonObject().get("id").toString().replace("\"",""));
                recommended_track.setExternal_url(i.getAsJsonObject().get("external_urls").getAsJsonObject().get("spotify").toString().replace("\"",""));

                List<SpoteeArtist> artists = new ArrayList<>();
                List<String> artist_names = new ArrayList<>();
                List<String> artist_ids = new ArrayList<>();

                for (JsonElement j : i.getAsJsonObject().get("artists").getAsJsonArray()){
                    SpoteeArtist artist = new SpoteeArtist();
                    artist.setArtist_id(j.getAsJsonObject().get("id").toString().replace("\"",""));
                    artist.setArtist_name(j.getAsJsonObject().get("name").toString().replace("\"",""));
                    artists.add(artist);
                }
                allRecommendations.add(recommended_track);
            }
        }
        con.disconnect();
        return allRecommendations;
    }


    public String getAccessToken(){
        return(this.access_token);
    }

}
