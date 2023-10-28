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

public class CardViewActivity extends AppCompatActivity {

    ImageView imageViewCard;
    Button buttonAddToInventory;
    Button buttonReturnToSearch;
    Card card;
    String cardImage;
    CardViewModel cardViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_view);

        cardViewModel = new ViewModelProvider(this).get(CardViewModel.class);

        Bundle extras = getIntent().getExtras();
        card = (Card) extras.getSerializable("Card");
        cardImage = card.images.getLargeImg();

        imageViewCard = findViewById(R.id.imageViewCard);
        Picasso.get().load(cardImage).into(imageViewCard);

        setupAddToInvButton();
        setupReturnToSearchButton();
    }

    private void setupAddToInvButton() {
        buttonAddToInventory = findViewById(R.id.buttonAddToInventory);
        buttonAddToInventory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Brain Fart", "Add to Inventory button pressed.");
                cardViewModel.insert(card);
                //TODO: this is throwing an error for some reason
                Toast.makeText(CardViewActivity.this, "Inv button pushed!", Toast.LENGTH_LONG).show();

            }
        });
    }

    private void setupReturnToSearchButton() {
        buttonReturnToSearch = findViewById(R.id.buttonReturnToSearch);
        buttonReturnToSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Brain Fart", "Return to Search button pushed");
                finish();
            }
        });
    }

}