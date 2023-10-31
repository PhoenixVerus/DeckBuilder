package CIS3334.deckbuilder;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * View holder for the inventory activity's recycler view;
 * displays a card saved in the database or "inventory"
 */
public class InventoryViewHolder extends RecyclerView.ViewHolder {

    TextView textViewName;
    TextView textViewRarity;
    TextView textViewTypes;
    ImageView imageViewCardResult;
    TextView textViewClass;
    TextView textViewSeries;
    Button buttonViewCard;

    /**
     * inventory view holder constructor
     * @param itemView view to be used
     */
    public InventoryViewHolder(@NonNull View itemView) {
        super(itemView);
        textViewName = itemView.findViewById(R.id.textViewName);
        textViewRarity = itemView.findViewById(R.id.textViewRarity);
        textViewTypes = itemView.findViewById(R.id.textViewTypes);
        imageViewCardResult = itemView.findViewById(R.id.imageViewCardInv);
        textViewClass = itemView.findViewById(R.id.textViewClass);
        textViewSeries = itemView.findViewById(R.id.textViewSeries);
        buttonViewCard = itemView.findViewById(R.id.buttonViewCard);
    }

}
