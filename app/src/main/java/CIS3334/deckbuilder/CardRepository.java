package CIS3334.deckbuilder;

import android.app.Application;
import androidx.lifecycle.LiveData;

import java.util.List;

/**
 * Repository for card objects that transition to the Room database
 */
public class CardRepository {
    private CardDao cardDao;

    private LiveData<List<Card>> cardList;

    /**
     * Initialization of the card repository
     * @param application
     */
    CardRepository(Application application) {
        CardDatabase db = CardDatabase.getDatabase(application);
        cardDao = db.cardDao();
        cardList = cardDao.getAll();
    }

    /**
     * Retrieves saved card objects and returns the livedata list
     * @return livedata list of card objects saved in database
     */
    LiveData<List<Card>> getAll() {
        CardDatabase.databaseWriteExecutor.execute(() -> {
            cardList = cardDao.getAll();
        });
        return cardList;
    }

    /**
     * Inserts a card into the database
     * @param card card to add
     */
    void insert(Card card) {
        CardDatabase.databaseWriteExecutor.execute(() -> {
            cardDao.insert(card);
        });
    }

    /**
     * Removes a card from the database
     * @param card card to be removed
     */
    void delete(Card card) {
        CardDatabase.databaseWriteExecutor.execute(() -> {
            cardDao.delete(card);
        });
    }

}
