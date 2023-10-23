package CIS3334.deckbuilder;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Class to hold the card search results from the API
 */
public class ResultViewHolder extends RecyclerView.ViewHolder {

    TextView textViewName;
    TextView textViewId;
    TextView textViewTypes;
    ImageView imageViewCardResult;
    TextView textViewClass;
    TextView textViewSeries;
    //Button buttonViewCard;

    public ResultViewHolder(@NonNull View itemView) {
        super(itemView);
        textViewName = itemView.findViewById(R.id.textViewName);
        textViewId = itemView.findViewById(R.id.textViewId);
        textViewTypes = itemView.findViewById(R.id.textViewTypes);
        imageViewCardResult = itemView.findViewById(R.id.imageViewCardResult);
        textViewClass = itemView.findViewById(R.id.textViewClass);
        textViewSeries = itemView.findViewById(R.id.textViewSeries);
        //buttonViewCard = itemView.findViewById(R.id.buttonViewCard);
    }

}
