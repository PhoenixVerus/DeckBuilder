package CIS3334.deckbuilder;

import android.app.Application;
import androidx.lifecycle.LiveData;

import java.util.List;

public class CardRepository {
    private CardDao cardDao;

    private LiveData<List<Card>> cardList;

    CardRepository(Application application) {
        CardDatabase db = CardDatabase.getDatabase(application);
        cardDao = db.cardDao();
        cardList = cardDao.getAll();
    }

    LiveData<List<Card>> getAll() {
        CardDatabase.databaseWriteExecutor.execute(() -> {
            cardList = cardDao.getAll();
        });
        return cardList;
    }

    void insert(Card card) {
        CardDatabase.databaseWriteExecutor.execute(() -> {
            cardDao.insert(card);
        });
    }

}
