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
 * Activity class to view a single card from the database or "inventory"
 */
public class InvCardActivity extends AppCompatActivity {

    ImageView imageViewCard;
    Button buttonDeleteFromInv;
    Button buttonBackToInv;
    Card card;
    String cardImage;
    InventoryViewModel inventoryViewModel;

    /**
     * Instantiates the inventory card activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inv_card);

        inventoryViewModel = new ViewModelProvider(this).get(InventoryViewModel.class);

        // Retrieval of intent and extras from previous activity
        Bundle extras = getIntent().getExtras();
        card = (Card) extras.getSerializable("Card");
        cardImage = card.images.getLargeImg();

        // Setup of activity elements
        imageViewCard = findViewById(R.id.imageViewCard);
        Picasso.get().load(cardImage).into(imageViewCard);
        setupDeleteFromInvButton();
        setupReturnToSearchButton();
    }

    /**
     * Sets up the Delete from Inventory button along with onclick listener to remove a
     * card from the database, and toast message confirming action
     */
    private void setupDeleteFromInvButton() {
        buttonDeleteFromInv = findViewById(R.id.buttonDeleteFromInv);
        buttonDeleteFromInv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Brain Fart", "Add to Inventory button pressed.");
                inventoryViewModel.delete(card);
                //TODO: this is throwing an error for some reason
                Toast.makeText(InvCardActivity.this, "Card deleted from inventory!", Toast.LENGTH_SHORT).show();

            }
        });
    }

    /**
     * Setup of Return to Search button that closes the activity onClick
     */
    private void setupReturnToSearchButton() {
        buttonBackToInv = findViewById(R.id.buttonBackToInv);
        buttonBackToInv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Brain Fart", "Return to Search button pushed");
                finish();
            }
        });
    }
}