package hr.fer.ppij.elvisai;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;

import java.io.IOException;
import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    static int activity_main_flag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        setContentView(R.layout.activity_main);


        activity_main_flag = 1;
    }

    public void changelayoutfindsong(View view) {
        setContentView(R.layout.song_recomm_main);
        activity_main_flag = 0;
    }
    public void changelayoutprofilecalib(View view) {
        setContentView(R.layout.song_calib);
        activity_main_flag = 0;
    }

    public void findNewSong(View view) throws IOException {
        Spotee spotee = new Spotee();
        //dummy example -- it can be used for #disp
        Map<String,Object> _parameters = new HashMap<String,Object>();
        _parameters.put("seed_artists", new String[]{"4NHQUGzhtTLFvgF5SZesLK", "0yujOFSHf3DlwirE8dsGuG"});
        _parameters.put("seed_tracks","0c6xIDDpzE81m2q797ordA");
        _parameters.put("min_energy",0.4);
        _parameters.put("min_popularity",50);
        List<SpoteeRecommendation> comends = spotee.getRecommendations(_parameters);
        for (SpoteeRecommendation spotii : comends) System.out.println(spotii);
        //This happens when you try to find new songs
    }

    public void spotifyRedirect(View view) {
        //This happens when you try to open spotify link
    }

    public void youtubeRedirect(View view) {
        //This happens when you try to open youtube link
    }

    public void deezerRedirect(View view) {
        //Who the hell uses Deezer??
    }

    public void thumbsUp(View view) {
        //This happens when you try to open spotify link
    }

    public void thumbsDown(View view) {
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