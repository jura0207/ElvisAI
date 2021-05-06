package hr.fer.ppij.elvisai;

public enum Genres {
    ROCK("rock"),
    CLASSICAL("classical"),
    POP("pop"),
    DANCE("dance"),
    CHILL("chill"),
    METAL("metal"),
    REGGAE("reggae"),
    RNB("r-n-b"),
    BLUES("blues"),
    JAZZ("jazz"),
    COUNTRY("country"),
    FOLK("folk"),
    PUNK("punk"),
    RAP("rap"),
    HOUSE("house");

    public final String value;

    private Genres(String value) {
        this.value = value;
    }
}
