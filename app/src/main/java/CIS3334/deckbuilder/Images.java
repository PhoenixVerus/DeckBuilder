package CIS3334.deckbuilder;

import java.io.Serializable;

/**
 * Subclass of card that holds the images for the card
 * implements serializable so data can be handed off with intents
 */
public class Images implements Serializable {
    public String small;
    public String large;

    /**
     * Images objet constructor
     * @param small string of small image URL
     * @param large string of large image URL
     */
    public Images(String small, String large) {
        this.small = small;
        this.large = large;
    }

    /**
     * Retrieves the small image URL
     * @return small image URL
     */
    public String getSmallImg() { return small; }

    /**
     * Retrieves the large image URL
     * @return large image URL
     */
    public String getLargeImg() { return large; }
}
