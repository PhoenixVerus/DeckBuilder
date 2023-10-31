package CIS3334.deckbuilder;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

/**
 * Inventory View Model class that ties the activity to model
 */
public class InventoryViewModel extends AndroidViewModel {

    private CardRepository cardRepository;
    private LiveData<List<Card>> cardList;

    /**
     * Instantiation of the view model
     * @param application
     */
    public InventoryViewModel(Application application) {
        super(application);
        cardRepository = new CardRepository(application);
        cardList = cardRepository.getAll();
    }

    /**
     * Removes a card from the card repository and in turn the database
     * @param card card to be removed from repository -> database
     */
    public void delete(Card card) {
        cardRepository.delete(card);
        cardList = cardRepository.getAll();
    }

    /**
     * Returns a LiveData list of all cards saved within the card repository -> database
     * @return LiveData list of cards in the repository -> database
     */
    public LiveData<List<Card>> getAllCards() {
        cardList = cardRepository.getAll();
        return cardList;
    }
}
