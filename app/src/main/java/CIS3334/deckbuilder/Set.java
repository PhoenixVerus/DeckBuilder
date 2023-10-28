package CIS3334.deckbuilder;

import java.io.Serializable;

public class Set implements Serializable {
    public String series;

    Set(String series) {
        this.series = series;
    }

    public String getSeries() { return series; }
}
