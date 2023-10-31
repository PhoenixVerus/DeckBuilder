package CIS3334.deckbuilder;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * The adapter that works with the inventory view holder to display
 * a card saved in the database or "inventory"
 */
public class InventoryAdapter extends RecyclerView.Adapter<InventoryViewHolder> {

    private List<Card> cards;

    /**
     * inventory adapter constructor
     */
    public InventoryAdapter() {
        cards = new ArrayList<>();
    }

    /**
     * Instantiates the inventory view holder that will work with the adapter and inflates it
     * @param parent The ViewGroup into which the new View will be added after it is bound to
     *               an adapter position.
     * @param viewType The view type of the new View.
     *
     * @return the inflated inventory view holder
     */
    @NonNull
    @Override
    public InventoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.search_result, parent, false);
        return new InventoryViewHolder(view);
    }

    /**
     * Binds the item views within the view holder with their card object counterparts
     * @param holder The ViewHolder which should be updated to represent the contents of the
     *        item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull InventoryViewHolder holder, int position) {
        holder.textViewName.setText(cards.get(position).getName());
        holder.textViewRarity.setText(cards.get(position).getRarity());
        holder.textViewTypes.setText(cards.get(position).getTypesString());
        holder.textViewSeries.setText(cards.get(position).getSet().getSeries());
        holder.textViewClass.setText(cards.get(position).getSupertype());
        Picasso.get().load(cards.get(position).images.getSmallImg()).into(holder.imageViewCardResult);

        //the "View" button which sends an intent to the inventory card activity to view the card in full
        holder.buttonViewCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Brain Fart", "View Card button clicked for card: " + cards.get(position).getName());
                Context context = view.getContext();
                Intent invCardIntent = new Intent(context, InvCardActivity.class);
                invCardIntent.putExtra("Card", cards.get(position));
                context.startActivity(invCardIntent);
            }
        });
    }

    /**
     * Retrieves the number of cards held in the cards arraylist
     * @return size of the cards arraylist
     */
    @Override
    public int getItemCount() {
        return cards.size();
    }

    /**
     * Updates the card list with the new updated list of cards
     * @param newCardList updated list of cards to display
     */
    public void updateCardList(List<Card> newCardList) { cards = newCardList; }

}
