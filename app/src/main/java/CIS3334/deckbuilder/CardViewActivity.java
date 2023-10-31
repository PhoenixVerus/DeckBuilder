package CIS3334.deckbuilder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

/**
 * Activity class to view a single card from the search results
 */
public class CardViewActivity extends AppCompatActivity {

    ImageView imageViewCard;
    Button buttonAddToInventory;
    Button buttonReturnToSearch;
    Card card;
    String cardImage;
    CardViewModel cardViewModel;

    /**
     * Instantiation of card view activity and elements
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_view);

        cardViewModel = new ViewModelProvider(this).get(CardViewModel.class);

        // Retrieve intent and extras from previous activity
        Bundle extras = getIntent().getExtras();
        card = (Card) extras.getSerializable("Card");
        cardImage = card.images.getLargeImg();

        //Setup elements
        imageViewCard = findViewById(R.id.imageViewCard);
        Picasso.get().load(cardImage).into(imageViewCard);
        setupAddToInvButton();
        setupReturnToSearchButton();
    }

    /**
     * Class to setup the "Add to Inventory" button along with onClick listener
     */
    private void setupAddToInvButton() {
        buttonAddToInventory = findViewById(R.id.buttonDeleteFromInv);
        buttonAddToInventory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Brain Fart", "Add to Inventory button pressed.");
                cardViewModel.insert(card);
                Toast.makeText(CardViewActivity.this, "Card added to inventory!", Toast.LENGTH_SHORT).show();

            }
        });
    }

    /**
     * Class to setup the "Return to Search" button along with onClick listener
     */
    private void setupReturnToSearchButton() {
        buttonReturnToSearch = findViewById(R.id.buttonBackToInv);
        buttonReturnToSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Brain Fart", "Return to Search button pushed");
                finish();
            }
        });
    }
}