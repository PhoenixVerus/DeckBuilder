package CIS3334.deckbuilder;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Main View Model class that ties the main activity to the model
 */
public class MainViewModel extends AndroidViewModel {

    private MutableLiveData<ArrayList<Card>> cardList;

    /**
     * Instantiation of the main view model
     * @param application
     */
    public MainViewModel(Application application) {
        super(application);
    }

    /**
     * Adds the new search results to the livedata list of search results
     * @param cards arraylist of cards from latest search
     */
    public void insert(ArrayList<Card> cards) {
        cardList.postValue(cards);
    }

    /**
     * Retrieves all cards held in the mutable live data; if it is null
     * a new mutable live data is created
     * @return mutablelivedata of arraylist of cards
     */
    public MutableLiveData<ArrayList<Card>> getAllCards() {
        if (cardList == null) {
            cardList = new MutableLiveData<ArrayList<Card>>();
        }
        return cardList;
    }

}
