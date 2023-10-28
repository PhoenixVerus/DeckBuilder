package CIS3334.deckbuilder;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.toolbox.JsonArrayRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/**
 * OLD: PokeApi class to call the Pokemon TCG API and retrieve card data;
 * used in previous Java course but appears to not work here; will most likely remove
 * if I can get Google Volley to work
 */
public class PokeApi {

    MainViewModel mainViewModel;
    private Gson gson;
    Context activityContext;
    Card retrievedCard;
    ArrayList<Card> cardList = new ArrayList<Card>();

    public PokeApi(Context activityContext, MainViewModel mainViewModel) {
        gson = new Gson();
        this.activityContext = activityContext;
        this.mainViewModel = mainViewModel;
    }

    /**
     * Method to retrieve a card from the PokeAPI
     * @return matching card
     */
    /*
    public void getCardWithVolley(String query) {
        // Define URL to use.
        String url = "https://api.pokemontcg.io/v2/cards/" + query;
        //String url = "https://api.pokemontcg.io/v2/cards?q=name:" + query;
        // test with fixed URL
        //url = "https://api.pokemontcg.io/v2/cards/xy1-1";
        Log.d("Brain Fart","Get card using url =  " + url);

        // Create a Volley web request to receive back a JSON object.
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url,null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response)
                    {
                        Log.d("Brain Fart","Received Response ");
                        String jsonFact= response.toString();
                        Log.d("Brain Fart","JSON =  "+jsonFact);

                        // Gson library module added for parsing json files
                        Gson gson = new Gson();
                        ApiDataWrapper data = gson.fromJson(jsonFact, ApiDataWrapper.class);
                        Card newCard = data.data;
                        Log.d("Brain Fart","Card read with name =  "+newCard.getName() );
                        Log.d("Brain Fart","Card read with name =  "+newCard.getId() );

                        retrievedCard = newCard;
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Brain Fart","error from JSON Request. VolleyError =  "+error.toString() );
                    }
                });

        // Create a RequestQueue used to send web requests using Volley
        RequestQueue queue = Volley.newRequestQueue(activityContext);
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(20 * 1000, 2, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        queue.add(jsonObjectRequest);
    }
     */

    public void getCardArrayWithVolley(String query) {
        String url = "https://api.pokemontcg.io/v2/cards?q=name:" + query + "&pageSize=3";
        Log.d("Brain Fart","Query input: " + url);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url,null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Log.d("Brain Fart","Received Response ");
                            String jsonFact= response.toString();
                            Log.d("Brain Fart","JSON =  "+jsonFact);
                            Gson gson = new Gson();
                            ApiArrayWrapper dataArray = gson.fromJson(jsonFact, ApiArrayWrapper.class);
                            Log.d("Brain Fart","Number of cards received =  "+dataArray.data.size());
                            // Loop through the array elements
                            for (Card newCard :dataArray.data) {
                                // Remember to add the following to the module build.gradle file for the gson library for parsing json files
                                // implementation 'com.google.code.gson:gson:2.10.1'//Card newCard = gson.fromJson(jsonFact, Card.class);
                                Log.d("Brain Fart","Card read with name =  "+newCard.getName()+" and id =  "+newCard.getId() );
                                retrievedCard = newCard;
                                mainViewModel.insert(retrievedCard);
                            }
                        } catch (Exception e) {
                            Log.d("Brain Fart", "In getCardArrayWithVolley -- JSONException = "+e.toString());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Brain Fart", "In getCardArrayWithVolley -- onErrorResponse = "+error);
                    }
                });

        RequestQueue queue = Volley.newRequestQueue(activityContext);
        // need this for timeout issue
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(20 * 1000, 2, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(jsonObjectRequest);
    }
}
