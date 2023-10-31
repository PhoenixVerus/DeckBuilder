package CIS3334.deckbuilder;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
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
 * Class to call the Pokemon TCG API and retrieve card data;
 * uses Google Volley to perform the API call
 */
public class PokeApi {

    MainViewModel mainViewModel;
    private Gson gson;
    Context activityContext;
    Card retrievedCard;
    ArrayList<Card> cardList = new ArrayList<Card>();

    /**
     * PokeApi construction
     * @param activityContext
     * @param mainViewModel view model used with the main activity
     */
    public PokeApi(Context activityContext, MainViewModel mainViewModel) {
        gson = new Gson();
        this.activityContext = activityContext;
        this.mainViewModel = mainViewModel;
    }

    /**
     * Makes the API call to the Pokemon TCG API and accepts back an object holding
     * an arraylist of card objects
     * @param query user's search inquiry
     */
    public void getCardArrayWithVolley(String query) {
        // URL to be used for API call; response size is limited to 5 to deal with performance issues and API call limits
        String url = "https://api.pokemontcg.io/v2/cards?q=name:\"" + query + "\"&pageSize=5";
        Log.d("Brain Fart","Query input: " + url);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url,null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            //received json response is converted into usable object
                            Log.d("Brain Fart","Received Response ");
                            String jsonFact= response.toString();
                            Log.d("Brain Fart","JSON =  "+jsonFact);
                            Gson gson = new Gson();
                            ApiArrayWrapper dataArray = gson.fromJson(jsonFact, ApiArrayWrapper.class);
                            Log.d("Brain Fart","Number of cards received =  "+dataArray.data.size());
                            // Loop through the array elements
                            for (Card newCard :dataArray.data) {
                                Log.d("Brain Fart","Card read with name =  "+newCard.getName()+" and id =  "+newCard.getId() );
                                retrievedCard = newCard;
                                cardList.add(newCard);
                            }
                            //converted object is sent to the main view model to update the mutable live data
                            mainViewModel.insert(cardList);
                        } catch (Exception e) {
                            Log.d("Brain Fart", "In getCardArrayWithVolley -- JSONException = " + e);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //listens for the network response and logs the code provided
                        NetworkResponse networkResponse = error.networkResponse;
                        Log.d("Brain Fart", "In onErrorResponse: " + networkResponse.statusCode);
                        Log.d("Brain Fart", "In getCardArrayWithVolley -- onErrorResponse = "+error);
                    }
                });

        RequestQueue queue = Volley.newRequestQueue(activityContext);
        // need this for timeout issue
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(20 * 1000, 2, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(jsonObjectRequest);
    }

}
