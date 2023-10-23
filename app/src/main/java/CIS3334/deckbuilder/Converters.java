package CIS3334.deckbuilder;

import static java.lang.Character.getType;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Converters {
    @TypeConverter
    public static ArrayList<String> fromString(String value) {
        Type listType = new TypeToken<ArrayList<String>>(){}.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String fromArrayList(ArrayList<String> list) {
        Gson gson = new Gson();
        String json = gson.toJson(list);
        return json;
    }

    @TypeConverter
    public static String fromImages(Images images) {
        Gson gson = new Gson();
        String json = gson.toJson(images);
        return json;
    }

    @TypeConverter
    public static Images fromStringToImages(String value) {
        Type listType = new TypeToken<Images>(){}.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String fromSet(Set set) {
        Gson gson = new Gson();
        String json = gson.toJson(set);
        return json;
    }

    @TypeConverter
    public static Set fromStringToSet(String value) {
        Type listType = new TypeToken<Set>(){}.getType();
        return new Gson().fromJson(value, listType);
    }

}
