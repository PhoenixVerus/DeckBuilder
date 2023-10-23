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

    public void insert(String id, String name, ArrayList<String> types, Images image, String supertype, Set set) {
        Card card = new Card(id, name, types, image, supertype, set);
        cardRepository.insert(card);
    }

    public LiveData<List<Card>> getAllCards() {
        cardList = cardRepository.getAll();
        return cardList;
    }
}
