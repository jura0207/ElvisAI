package hr.fer.ppij.elvisai;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    static int activity_main_flag;
    static SpoteeRecommendation current;
    static Emotions selectedEmotion;
    static List<Genres> selectedGenres;

    static List<SpoteeRecommendation> comends;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        selectedGenres = new ArrayList<>();
        setContentView(R.layout.activity_main);

        activity_main_flag = 1;
    }

    public void landingContinueAction(View view) throws IOException {
        //forwarding people to emotion_picker
        setContentView(R.layout.emotion_picker);
        activity_main_flag = 0;
    }




    public void findNewSong(View view) throws IOException {
        TextView songNameView = (TextView)findViewById(R.id.textView13);
        TextView songArtistView = (TextView)findViewById(R.id.textView14);
        TextView songAlbumView = (TextView)findViewById(R.id.textView15);
        TextView songYearView = (TextView)findViewById(R.id.textView16);
        ImageView albumImageView = (ImageView)findViewById(R.id.imageView2);
        if (comends.isEmpty())
            //TODO: this should be replaced as following method will be removed
            recommend(view);
        current = comends.get(0);
        songNameView.setText(current.getName());
        songArtistView.setText(current.getArtists().get(0).getArtist_name());
        songAlbumView.setText(current.getAlbum_name());
        songYearView.setText(current.getRelease_date().substring(0, 4));
        URL url = new URL(current.getCover_art_link());
        Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
        albumImageView.setImageBitmap(bmp);
        comends.remove(0);
        //This happens when you try to find new songs
    }

    /************************ emotion_picker onClickActions ***************************************/
    public void happyButtonAction(View view){
        selectedEmotion = Emotions.HAPPY;
        setContentView(R.layout.genre_picker);
    }
    public void sadButtonAction(View view){
        selectedEmotion = Emotions.SAD;
        setContentView(R.layout.genre_picker);
    }
    public void chillButtonAction(View view){
        selectedEmotion = Emotions.CHILL;
        setContentView(R.layout.genre_picker);
    }
    public void pumpedButtonAction(View view){
        selectedEmotion = Emotions.PUMPED;
        setContentView(R.layout.genre_picker);
    }
    public void angryButtonAction(View view){
        selectedEmotion = Emotions.ANGRY;
        setContentView(R.layout.genre_picker);
    }
    public void tiredButtonAction(View view){
        selectedEmotion = Emotions.TIRED;
        setContentView(R.layout.genre_picker);
    }
    /**********************************************************************************************/

    /******************** genre_picker onClickActions *********************************************/

    public void recommend(View view) throws IOException {
        Spotee spotee = new Spotee();
        Map<String,Object> _parameters = new HashMap<String,Object>();
        Map<String,Float> emotionParams = selectedEmotion.getEmotionParameters();

        //ideally returns String[] of selected genres, TODO: TEST
        String[] genreParameters = new String[selectedGenres.size()];
        for (int i = 0; i < selectedGenres.size();i++){
            genreParameters[i] = selectedGenres.get(i).getValue();
        }

        _parameters.put("seed_genres", genreParameters);
        if (emotionParams.get("danceability") != null)
            _parameters.put("target_danceability", emotionParams.get("danceability"));
        if (emotionParams.get("energy") != null)
            _parameters.put("target_energy", emotionParams.get("energy"));
        if (emotionParams.get("instrumentalness") != null)
            _parameters.put("target_instrumentalness", emotionParams.get("instrumentalness"));
        if (emotionParams.get("tempo") != null)
            _parameters.put("target_tempo", emotionParams.get("tempo"));

        comends = spotee.getRecommendations(_parameters);

        setContentView(R.layout.song_recomm_main);
        findNewSong(view);

    }
    // Obviously dumb way to do this, but no time and impossible to break
    public void rockButtonAction(View view) throws IOException {
        selectedGenres.add(Genres.ROCK);
        if (selectedGenres.size() == 5) recommend(view);
    }
    public void classicalButtonAction(View view) throws IOException {
        selectedGenres.add(Genres.CLASSICAL);
        if (selectedGenres.size() == 5) recommend(view);
    }
    public void popButtonAction(View view) throws IOException {
        selectedGenres.add(Genres.POP);
        if (selectedGenres.size() == 5) recommend(view);
    }
    public void danceButtonAction(View view) throws IOException {
        selectedGenres.add(Genres.DANCE);
        if (selectedGenres.size() == 5) recommend(view);
    }
    // not named in the same manner
    public void chillGenreButtonAction(View view) throws IOException {
        selectedGenres.add(Genres.CHILL);
        if (selectedGenres.size() == 5) recommend(view);
    }
    public void metalButtonAction(View view) throws IOException {
        selectedGenres.add(Genres.METAL);
        if (selectedGenres.size() == 5) recommend(view);
    }
    public void reggaeButtonAction(View view) throws IOException {
        selectedGenres.add(Genres.REGGAE);
        if (selectedGenres.size() == 5) recommend(view);
    }
    public void rnbButtonAction(View view) throws IOException {
        selectedGenres.add(Genres.RNB);
        if (selectedGenres.size() == 5) recommend(view);
    }
    public void bluesButtonAction(View view) throws IOException {
        selectedGenres.add(Genres.BLUES);
        if (selectedGenres.size() == 5) recommend(view);
    }
    public void jazzButtonAction(View view) throws IOException {
        selectedGenres.add(Genres.JAZZ);
        if (selectedGenres.size() == 5) recommend(view);
    }
    public void countryButtonAction(View view) throws IOException {
        selectedGenres.add(Genres.COUNTRY);
        if (selectedGenres.size() == 5) recommend(view);
    }
    public void folkButtonAction(View view) throws IOException {
        selectedGenres.add(Genres.FOLK);
        if (selectedGenres.size() == 5) recommend(view);
    }
    public void punkButtonAction(View view) throws IOException {
        selectedGenres.add(Genres.PUNK);
        if (selectedGenres.size() == 5) recommend(view);
    }
    public void rapButtonAction(View view) throws IOException {
        selectedGenres.add(Genres.RAP);
        if (selectedGenres.size() == 5) recommend(view);
    }
    public void houseButtonAction(View view) throws IOException {
        selectedGenres.add(Genres.HOUSE);
        if (selectedGenres.size() == 5) recommend(view);
    }
    /**********************************************************************************************/

    public void spotifyRedirect(View view) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);
        intent.setData(Uri.parse(current.external_url));
        startActivity(intent);
        //This happens when you try to open spotify link
    }


    @Override
    public void onBackPressed(){
        if(activity_main_flag==0){
            setContentView(R.layout.activity_main);
            activity_main_flag = 1;
        } else{
            finish();
        }
    }
}