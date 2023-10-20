package CIS3334.deckbuilder;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Card {

    @PrimaryKey (autoGenerate = true)
    public Integer dbId;

    //This is the id that the API uses to identify each card
    public String id;
    public String name;
    //public String types;

    public Card(String id, String name){
        this.id = id;
        this.name = name;
        //this.types = types;
    }

    public Card(){
        id = "";
        name = "";
        //types = "";
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    /*public String getTypes() {
        String allTypes = "";
        for(String type : types) {
            allTypes += type + "\n";
        }
        return allTypes;
    }*/
}
