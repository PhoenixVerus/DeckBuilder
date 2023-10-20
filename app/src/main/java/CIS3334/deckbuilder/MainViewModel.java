package CIS3334.deckbuilder;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.ArrayList;
import java.util.List;

public class MainViewModel extends AndroidViewModel {

    private CardRepository cardRepository;
    private LiveData<List<Card>> cardList;

    public MainViewModel(Application application) {
        super(application);
        cardRepository = new CardRepository(application);
        cardList = cardRepository.getAll();
    }

    public void insert(String id, String name) {
        Card card = new Card(id, name);
        cardRepository.insert(card);
    }

    public LiveData<List<Card>> getAllCards() {
        cardList = cardRepository.getAll();
        return cardList;
    }
}
