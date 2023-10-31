package CIS3334.deckbuilder;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

/**
 * Activity class that displays the list of all card objects saved
 * in the database or "inventory"
 */
public class InventoryActivity extends AppCompatActivity {

    RecyclerView recyclerViewInventory;
    InventoryViewModel inventoryViewModel;
    InventoryAdapter inventoryAdapter;
    Button buttonBackToSearch;

    /**
     * Instantiation of the inventory activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);

        inventoryViewModel = new ViewModelProvider(this).get(InventoryViewModel.class);

        //Setup of the activity elements
        setupRecyclerViewInventory();
        setupLiveDataObserver();
        setupBackToSearchButton();
    }

    /**
     * Sets up the recycler view that holds each card object saved in the database;
     * the view is setup so the most recent items will be shown at the top
     */
    private void setupRecyclerViewInventory() {
        recyclerViewInventory = findViewById(R.id.recyclerViewInventory);
        inventoryAdapter = new InventoryAdapter();
        recyclerViewInventory.setAdapter(inventoryAdapter);
        recyclerViewInventory.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,true));
    }

    /**
     * Sets up the live data observer that will listen for changes in the LiveData list of
     * cards and will update the recycler view with new card objects added;
     * the view is set so it will reposition to the top of the list
     */
    private void setupLiveDataObserver() {
        inventoryViewModel.getAllCards().observe(this, new Observer<List<Card>>() {
            @Override
            public void onChanged(@Nullable List<Card> cards) {
                Log.d("Brain Fart", "MainActivity -- LiveData Observer -- Number of cards = "+cards.size());
                inventoryAdapter.updateCardList(cards);
                recyclerViewInventory.scrollToPosition(cards.size() - 1);
                inventoryAdapter.notifyDataSetChanged();
            }
        });
    }

    /**
     * Sets up the "Back to Search" button which closes the inventory activity
     */
    private void setupBackToSearchButton() {
        buttonBackToSearch = findViewById(R.id.buttonBackToSearch);
        buttonBackToSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Brain Fart", "Return to Search button pushed");
                finish();
            }
        });
    }
}