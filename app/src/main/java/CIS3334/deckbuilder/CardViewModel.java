package CIS3334.deckbuilder;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.ArrayList;
import java.util.List;

public class CardViewModel extends AndroidViewModel {

    private CardRepository cardRepository;
    private LiveData<List<Card>> cardList;

    public CardViewModel(Application application) {
        super(application);
        cardRepository = new CardRepository(application);
        cardList = cardRepository.getAll();
    }

    public void insert(Card card) {
        cardRepository.insert(card);
        cardList = cardRepository.getAll();
    }

    public LiveData<List<Card>> getAllCards() {
        cardList = cardRepository.getAll();
        return cardList;
    }
}
