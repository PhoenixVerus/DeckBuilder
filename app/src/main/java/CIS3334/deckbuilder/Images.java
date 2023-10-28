package CIS3334.deckbuilder;

import java.io.Serializable;

public class Images implements Serializable {
    public String small;
    public String large;

    public Images(String small, String large) {
        this.small = small;
        this.large = large;
    }

    public String getSmallImg() { return small; }

    public String getLargeImg() { return large; }
}
