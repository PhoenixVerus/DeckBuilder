package CIS3334.deckbuilder;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.ArrayList;

@Entity
public class Card implements Serializable {

    @PrimaryKey (autoGenerate = true)
    public Integer dbId;

    //This is the id that the API uses to identify each card
    public String id;
    public String name;
    public ArrayList<String> types;
    public Images images;
    public String supertype;
    public Set set;

    public Card(String id, String name, ArrayList<String> types, Images images, String supertype, Set set){
        this.id = id;
        this.name = name;
        this.types = types;
        this.images = images;
        this.supertype = supertype;
        this.set = set;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    // Return the Types array as a single string
    public String getTypesString() {
        String allTypes = "";
        for(String type : types) {
            allTypes += type + "\n";
        }
        return allTypes;
    }

    public ArrayList<String> getTypes() { return types; }

    public Images getImages() {
        return images;
    }

    public String getSupertype() { return supertype; }

    public Set getSet() { return set; }


}
