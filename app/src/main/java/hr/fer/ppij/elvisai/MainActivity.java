package hr.fer.ppij.elvisai;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    static int activity_main_flag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

    public void findNewSong(View view) {
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