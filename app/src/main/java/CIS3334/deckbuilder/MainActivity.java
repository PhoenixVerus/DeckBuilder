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
import android.widget.Toast;

import java.util.List;

/**
 * The Main Activity class which holds the first and primary screen the
 * user views and interacts with; from here, users search for cards
 * from the Pokemon TCG API and search results are shown in a list
 */
public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerViewSearchResults;
    ResultAdapter resultsAdapter;
    MainViewModel mainViewModel;
    EditText editTextSearch;
    Button buttonViewInventory;
    Button buttonSearch;
    PokeApi pokeApi;

    /**
     * Instantiate the main activity and elements
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);

        //Setup of activity elements
        buttonViewInventory = findViewById(R.id.buttonViewInventory);
        editTextSearch = findViewById(R.id.editTextSearch);
        pokeApi = new PokeApi(this, mainViewModel);
        setupButtonSearch();
        setupRecyclerViewResults();
        setupLiveDataObserver();
        setupButtonViewInventory();
    }

    /**
     * Method setting up the search button, which calls the getCardsWithVolley()
     * method from pokeAPI which in turn makes an API call
     */
    private void setupButtonSearch() {
        buttonSearch = findViewById(R.id.buttonSearch);
        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Brain Fart", "Button is clicked");
                String query = editTextSearch.getText().toString();
                Log.d("Brain Fart", "Text of query fetched");
                try {
                    //query set to pokeApi for the API request
                    pokeApi.getCardArrayWithVolley(query);
                    Log.d("Brain Fart", "Calling API with GetCardWithVolley");
                    Toast.makeText(MainActivity.this, "Searching for cards...", Toast.LENGTH_SHORT).show();
                    Log.d("Brain Fart", "Card has been retrieved with name = " + pokeApi.retrievedCard.getName());

                } catch (Exception e) {
                    e.printStackTrace();
                    Log.d("Brain Fart", "Query failed to call.");
                }
            }
        });
    }

    /**
     * Setting up the recycler view which will show the search results;
     * the view is setup so the most recent items will be shown at the top
     */
    private void setupRecyclerViewResults() {
        recyclerViewSearchResults = findViewById(R.id.recyclerViewSearchResults);
        resultsAdapter = new ResultAdapter();
        recyclerViewSearchResults.setAdapter(resultsAdapter);
        recyclerViewSearchResults.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,true));
    }

    /**
     * Setting up the Live Data Observer so the recycler view will update
     * with newly added information; the view is set so it will reposition to the top of the list
     */
    private void setupLiveDataObserver() {
        mainViewModel.getAllCards().observe(this, new Observer<List<Card>>() {
            @Override
            public void onChanged(@Nullable List<Card> cards) {
                Log.d("Brain Fart", "MainActivity -- LiveData Observer -- Number of cards = "+cards.size());
                resultsAdapter.updateCardList(cards);
                recyclerViewSearchResults.scrollToPosition(cards.size() - 1);
                resultsAdapter.notifyDataSetChanged();
            }
        });
    }

    /**
     * Setup of the "View Your Inventory" button which will open a different activity
     * to see the cards the user has saved to their "inventory"
     */
    private void setupButtonViewInventory() {
        buttonViewInventory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Brain Fart", "Inventory button is clicked");
                Context context = v.getContext();
                Intent inventoryIntent = new Intent(context, InventoryActivity.class);
                context.startActivity(inventoryIntent);
            }
        });
    }
}