package hr.fer.ppij.elvisai;

public enum Tempo {
    SLOW(80.f),
    MEDIUM(100.f),
    FAST(130.f);

    public float getBpm() {
        return bpm;
    }

    private float bpm;

    private Tempo(float bpm){
        this.bpm = bpm;
    }
}
