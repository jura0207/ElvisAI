package hr.fer.ppij.elvisai;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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
import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    static int activity_main_flag;
    static SpoteeRecommendation current;
    static List<SpoteeRecommendation> comends;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        setContentView(R.layout.activity_main);

        activity_main_flag = 1;
    }

    public void changelayoutfindsong(View view) throws IOException {
        setContentView(R.layout.song_recomm_main);
        activity_main_flag = 0;
        getRecommendations();
        findNewSong(view);
    }

    public void changelayoutprofilecalib(View view) {
        setContentView(R.layout.song_calib);
        activity_main_flag = 0;
    }

    private void getRecommendations() throws IOException {
        Spotee spotee = new Spotee();
        //dummy example -- it can be used for #disp
        Map<String,Object> _parameters = new HashMap<String,Object>();
        _parameters.put("seed_artists", new String[]{"4NHQUGzhtTLFvgF5SZesLK", "0yujOFSHf3DlwirE8dsGuG"});
        _parameters.put("seed_tracks","0c6xIDDpzE81m2q797ordA");
        _parameters.put("min_energy",0.4);
        _parameters.put("min_popularity",50);
        comends = spotee.getRecommendations(_parameters);
        for (SpoteeRecommendation spotii : comends) System.out.println(spotii);
    }

    public void findNewSong(View view) throws IOException {
        TextView songNameView = (TextView)findViewById(R.id.textView13);
        TextView songArtistView = (TextView)findViewById(R.id.textView14);
        TextView songAlbumView = (TextView)findViewById(R.id.textView15);
        TextView songYearView = (TextView)findViewById(R.id.textView16);
        ImageView albumImageView = (ImageView)findViewById(R.id.imageView2);
        if (comends.isEmpty())
            getRecommendations();
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

    public void spotifyRedirect(View view) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);
        intent.setData(Uri.parse(current.external_url));
        startActivity(intent);
        //This happens when you try to open spotify link
    }

    public void youtubeRedirect(View view) {
        //This happens when you try to open youtube link
    }

    public void deezerRedirect(View view) {
        //Who the hell uses Deezer??
    }

    public void thumbsUp(View view) {
        //Thumbs up button
    }

    public void thumbsDown(View view) {
        //Thumbs down button
    }

    public void playButton(View view) {
        //Thumbs down button
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