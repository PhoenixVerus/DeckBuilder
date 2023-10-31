package CIS3334.deckbuilder;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.ArrayList;
import java.util.List;

/**
 * Card View Model class that ties the card view activity to model
 */
public class CardViewModel extends AndroidViewModel {

    private CardRepository cardRepository;

    /**
     * CardViewModel constructor
     * @param application
     */
    public CardViewModel(Application application) {
        super(application);
        cardRepository = new CardRepository(application);
    }

    /**
     * Class to insert card into the card repository which passes along to the database
     * @param card card to insert
     */
    public void insert(Card card) {
        cardRepository.insert(card);
    }
}
