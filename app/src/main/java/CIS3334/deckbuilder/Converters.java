package CIS3334.deckbuilder;

import static java.lang.Character.getType;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Converter class so the database and application know how to handle created objects
 * being passed between them
 */
public class Converters {

    /**
     * Converts a string value into an arraylist to feed into the application
     * @param value string to be converted
     * @return converted arraylist
     */
    @TypeConverter
    public static ArrayList<String> fromString(String value) {
        Type listType = new TypeToken<ArrayList<String>>(){}.getType();
        return new Gson().fromJson(value, listType);
    }

    /**
     * Converts an arraylist into a string -> json to feed into the database
     * @param list arraylist to be converted
     * @return converted json
     */
    @TypeConverter
    public static String fromArrayList(ArrayList<String> list) {
        Gson gson = new Gson();
        String json = gson.toJson(list);
        return json;
    }

    /**
     * Converts an images object into a string to feed into the database
     * @param images images object to be converted
     * @return converted json
     */
    @TypeConverter
    public static String fromImages(Images images) {
        Gson gson = new Gson();
        String json = gson.toJson(images);
        return json;
    }

    /**
     * Converts a string (json) into an images object for the application
     * @param value string to be converted
     * @return converted images object
     */
    @TypeConverter
    public static Images fromStringToImages(String value) {
        Type listType = new TypeToken<Images>(){}.getType();
        return new Gson().fromJson(value, listType);
    }

    /**
     * Converts a set object into a string (json) for the database
     * @param set set object to be converted
     * @return converted json
     */
    @TypeConverter
    public static String fromSet(Set set) {
        Gson gson = new Gson();
        String json = gson.toJson(set);
        return json;
    }

    /**
     * Converts a string (json) from the database into a set object
     * @param value string to be converted
     * @return converted set object
     */
    @TypeConverter
    public static Set fromStringToSet(String value) {
        Type listType = new TypeToken<Set>(){}.getType();
        return new Gson().fromJson(value, listType);
    }

}
