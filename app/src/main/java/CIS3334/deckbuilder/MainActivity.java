package CIS3334.deckbuilder;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerViewSearchResults;
    ResultAdapter resultsAdapter;
    MainViewModel mainViewModel;
    EditText editTextSearch;
    Button buttonViewInventory;
    Button buttonSearch;
    PokeApi pokeApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);

        editTextSearch = findViewById(R.id.editTextSearch);
        buttonViewInventory = findViewById(R.id.buttonViewInventory);
        pokeApi = new PokeApi(this, mainViewModel);

        setupButtonSearch();
        setupRecyclerViewResults();
        setupLiveDataObserver();
        setupButtonViewInventory();
    }

    /**
     * Method setting up the search button, which calls the getCardsWithVolley()
     * method with in turn makes an API call
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
                    //pokeApi.getCardWithVolley(query);
                    pokeApi.getCardArrayWithVolley(query);
                    Log.d("Brain Fart", "Card not ready ... calling GetCardWithVolley");
                    // TODO: Listen for JSON response and then push info to the view model

                    Log.d("Brain Fart", "Card has been retrieved with name = " + pokeApi.retrievedCard.getName());

                } catch (Exception e) {
                    e.printStackTrace();
                    Log.d("Brain Fart", "Query failed to call.");
                }
            }
        });
    }

    /**
     * Setting up the recycler view which will show the search results
     */
    private void setupRecyclerViewResults() {
        recyclerViewSearchResults = findViewById(R.id.recyclerViewSearchResults);
        resultsAdapter = new ResultAdapter();
        recyclerViewSearchResults.setAdapter(resultsAdapter);
        recyclerViewSearchResults.setLayoutManager(new LinearLayoutManager(this));
    }

    /**
     * Setting up the Live Data Observer so the recycler view will update
     * with newly added information
     */
    private void setupLiveDataObserver() {
        mainViewModel.getAllCards().observe(this, new Observer<List<Card>>() {
            @Override
            public void onChanged(@Nullable List<Card> cards) {
                Log.d("Brain Fart", "MainActivity -- LiveData Observer -- Number of cards = "+cards.size());
                resultsAdapter.updateCardList(cards);
                resultsAdapter.notifyDataSetChanged();
            }
        });
    }

    /**
     * Setup of View Your Inventory which will open a different activity
     * to see the cards the user has saved to their "inventory"
     */
    private void setupButtonViewInventory() {
        buttonViewInventory = findViewById(R.id.buttonViewInventory);
        buttonViewInventory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Brain Fart", "Inventory button is clicked");

            }
        });
    }

}