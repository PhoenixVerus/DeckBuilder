package CIS3334.deckbuilder;

import java.io.Serializable;

/**
 * Subclass of card that holds the images for the card
 * implements serializable so data can be handed off with intents
 */
public class Set implements Serializable {

    public String series;

    /**
     * set object constructor
     * @param series
     */
    public Set(String series) {
        this.series = series;
    }

    /**
     * Retrieves the series name of the set
     * @return string of series name
     */
    public String getSeries() { return series; }
}
