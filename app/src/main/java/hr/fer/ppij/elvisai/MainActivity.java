 package hr.fer.ppij.elvisai;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

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
    static boolean engLanguage = false;

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
        if (engLanguage == true){
            setEngLanguage(1);
        }
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
        if (engLanguage == true){
            setEngLanguage(2);
        }
    }
    public void sadButtonAction(View view){
        selectedEmotion = Emotions.SAD;
        setContentView(R.layout.genre_picker);
        if (engLanguage == true){
            setEngLanguage(2);
        }
    }
    public void chillButtonAction(View view){
        selectedEmotion = Emotions.CHILL;
        setContentView(R.layout.genre_picker);
        if (engLanguage == true){
            setEngLanguage(2);
        }
    }
    public void pumpedButtonAction(View view){
        selectedEmotion = Emotions.PUMPED;
        setContentView(R.layout.genre_picker);
        if (engLanguage == true){
            setEngLanguage(2);
        }
    }
    public void angryButtonAction(View view){
        selectedEmotion = Emotions.ANGRY;
        setContentView(R.layout.genre_picker);
        if (engLanguage == true){
            setEngLanguage(2);
        }
    }
    public void tiredButtonAction(View view){
        selectedEmotion = Emotions.TIRED;
        setContentView(R.layout.genre_picker);
        if (engLanguage == true){
            setEngLanguage(2);
        }
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
        if (engLanguage == true){
            setEngLanguage(3);
        }

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

    public void youtubeRedirect(View view) {
        try {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("youtube://www.youtube.com/results?search_query=" + current.getName()
                    + "+" + current.getArtists().get(0).getArtist_name()
                    + "+" + current.getAlbum_name()));
            startActivity(browserIntent);
        } catch (Exception e) {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/results?search_query=" + current.getName()
                    + "+" + current.getArtists().get(0).getArtist_name()
                    + "+" + current.getAlbum_name()));
            startActivity(browserIntent);
        }
    }

    public void deezerRedirect(View view){
        try {
            /**Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("deezer://www.deezer.com/search/"+current.getName()
                    + "%20" + current.getArtists().get(0).getArtist_name()
                    + "%20" + current.getAlbum_name()));*/
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.deezer.com/search/"+current.getName()
                    + "%20" + current.getArtists().get(0).getArtist_name()));
            startActivity(intent);
        } catch (Exception e) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.deezer.com/search/"+current.getName()
                    + "%20" + current.getArtists().get(0).getArtist_name()));
            startActivity(intent);
        }
    }

    public void changeLanguage(View view){
        if (engLanguage == false){
            setEngLanguage(0);
            engLanguage = true;
        } else{
            setEngLanguage(-1);
            engLanguage = false;
        }

    }

    public void setEngLanguage(int viewNumber){
        if (viewNumber == 0){
            TextView welcomeTextView = (TextView)findViewById(R.id.welcome);
            welcomeTextView.setText("Welcome to");
            TextView musicWithoutTextView = (TextView)findViewById(R.id.textView2);
            musicWithoutTextView.setText("Music without advertisment");
            Button buttonFindSong = (Button)findViewById(R.id.button2);
            buttonFindSong.setText("Find song");
        } else if (viewNumber == 1){
            TextView howRUTextView = (TextView)findViewById(R.id.how_ru_feeling_text_view);
            howRUTextView.setText("How are you feeling?");
            Button buttonHappy = (Button)findViewById(R.id.happy_btn);
            buttonHappy.setText("Happy");
            Button buttonSad = (Button)findViewById(R.id.sad_btn);
            buttonSad.setText("Sad");
            Button buttonRelaxed = (Button)findViewById(R.id.relaxed_btn);
            buttonRelaxed.setText("Relaxed");
            Button buttonPumpedUp = (Button)findViewById(R.id.pumpedUp_btn);
            buttonPumpedUp.setText("Pumped up");
            Button buttonAngry = (Button)findViewById(R.id.angry_btn);
            buttonAngry.setText("Angry");
            Button buttonTired = (Button)findViewById(R.id.tired_btn);
            buttonTired.setText("Tired");
        } else if (viewNumber == 2){
            Button buttonContinue = (Button)findViewById(R.id.emotion_continue_btn);
            buttonContinue.setText("Find");
            TextView whichMusicGenreTextView = (TextView)findViewById(R.id.which_music_text_view);
            whichMusicGenreTextView.setText("Which music genres do you like?");
        } else if (viewNumber == 3){
            TextView songNameTextView = (TextView)findViewById(R.id.textView9);
            songNameTextView.setText("Song name:");
            TextView artistNameTextView = (TextView)findViewById(R.id.textView10);
            artistNameTextView.setText("Artist:");
            TextView albumNameTextView = (TextView)findViewById(R.id.textView11);
            albumNameTextView.setText("Album:");
            TextView yearTextView = (TextView)findViewById(R.id.textView12);
            yearTextView.setText("Year:");
            TextView findNewSongTextView = (TextView)findViewById(R.id.textView7);
            findNewSongTextView.setText("Find new song:");
        } else if (viewNumber == -1){
            TextView welcomeTextView = (TextView)findViewById(R.id.welcome);
            welcomeTextView.setText("Dobrodošli u");
            TextView musicWithoutTextView = (TextView)findViewById(R.id.textView2);
            musicWithoutTextView.setText("Glazba bez promidžbe");
            Button buttonFindSong = (Button)findViewById(R.id.button2);
            buttonFindSong.setText("Pronađi pjesmu");
        }

    }


    @Override
    public void onBackPressed(){
        current = null;
        selectedEmotion = null;
        selectedGenres.clear();
        if(activity_main_flag==0){
            setContentView(R.layout.activity_main);
            activity_main_flag = 1;
            if (engLanguage == true){
                ToggleButton toggleButton = (ToggleButton)findViewById(R.id.toggleButton);
                toggleButton.performClick();
                setEngLanguage(0);
                engLanguage=true;
            }
        } else{
            finish();
        }
    }
}