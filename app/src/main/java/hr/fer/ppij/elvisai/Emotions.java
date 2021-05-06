
package hr.fer.ppij.elvisai;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum Emotions {
    /* Emotions interpreattion config */
    //TODO: figure out what to do with insturmentalness
    HAPPY(0.75f,0.75f,null, Tempo.FAST.getBpm()),
    SAD(0.35f,0.35f,null, Tempo.SLOW.getBpm()),
    CHILL(null,0.35f,null, null),
    PUMPED(null,0.75f,null, Tempo.FAST.getBpm()),
    ANGRY(0.35f,0.75f,null, null),
    TIRED(0.35f,null,null, Tempo.SLOW.getBpm());

    public final Float danceability;
    public final Float energy;
    public final Float instrumentalness;

    public Float getDanceability() {
        return danceability;
    }

    public Float getEnergy() {
        return energy;
    }

    public Float getInstrumentalness() {
        return instrumentalness;
    }

    public Float getTempo() {
        return tempo;
    }

    public Map<String,Float> getEmotionParameters(){
        Map<String,Float> mapOutput = new HashMap<>();
        mapOutput.put("danceability",this.getDanceability());
        mapOutput.put("energy",this.getEnergy());
        mapOutput.put("instrumentalness",this.getInstrumentalness());
        mapOutput.put("tempo",this.getTempo());

        return mapOutput;
    }

    public final Float tempo;


    Emotions(Float danceability, Float energy, Float instrumentalness, Float tempo) {
        this.danceability = danceability;
        this.energy = energy;
        this.instrumentalness = instrumentalness;
        this.tempo = tempo;
    }


}
