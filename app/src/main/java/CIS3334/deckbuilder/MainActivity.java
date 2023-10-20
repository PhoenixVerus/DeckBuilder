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
    TextView textViewTest;
    //PokeApi pokeApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);

        editTextSearch = findViewById(R.id.editTextSearch);
        buttonViewInventory = findViewById(R.id.buttonViewInventory);
        textViewTest = findViewById(R.id.textViewTest);
        //pokeApi = new PokeApi();

        setupButtonSearch();
        setupRecyclerViewResults();
        setupLiveDataObserver();
        setupButtonViewInventory();
    }

    /**
     * Method setting up the search button, which calls the getCards()
     * method with in turn makes an API call
     */
    private void setupButtonSearch() {
        buttonSearch = findViewById(R.id.buttonSearch);
        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Brain Fart", "Button is clicked");
                getCards();
            }
        });
    }

    /**
     * Method to call the Pokemon TCG API and return card information
     * Currently having problems with the emulator running this, will try with
     * a physical phone when I can
     */
    private void getCards() {
        //Getting the query the user has typed in to use it in our API search
        String query = editTextSearch.getText().toString();
        //String url = "https://api.pokemontcg.io/v2/cards?=name:" + query;
        String url = "https://api.pokemontcg.io/v2/cards/" + query;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        String jsonCard = response.toString();
                        Gson gson = new Gson();
                        Card card = gson.fromJson(jsonCard, Card.class);
                        textViewTest.setText(card.getName());
                    }
                },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        textViewTest.setText("ERROR Response: " + error.toString());
                    }
                });

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(jsonObjectRequest);
    }

    /**
     * Setting up the recycler view which will show the search results
     */
    private void setupRecyclerViewResults() {
        recyclerViewSearchResults = findViewById(R.id.recyclerViewSearchResults);
        resultsAdapter = new ResultAdapter(getApplication(),mainViewModel);
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
     * CURRENTLY being used as a testing dummy to make sure the recycler view
     * is working
     */
    private  void setupButtonViewInventory() {
        buttonViewInventory = findViewById(R.id.buttonViewInventory);
        buttonViewInventory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Brain Fart", "Inventory button is clicked");
                //hard-coded card info to test the GUI setup
                mainViewModel.insert("id here", "Charizard");
                //test line to see if the button is actually interacting with the GUI
                textViewTest.setText("Charizard? Where are ya, buddy?");
            }
        });
    }

}