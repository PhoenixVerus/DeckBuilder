package CIS3334.deckbuilder;

import android.app.Application;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import com.squareup.picasso.Picasso;

/**
 * Class to send information to the results view holder
 */
public class ResultAdapter extends RecyclerView.Adapter<ResultViewHolder> {

    private List<Card> cards;

    public ResultAdapter() {
        cards = new ArrayList<>();
    }

    @NonNull
    @Override
    public ResultViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int ViewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.search_result, parent, false);
        return new ResultViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ResultViewHolder holder, int position) {
        holder.textViewName.setText(cards.get(position).getName());
        holder.textViewId.setText(cards.get(position).getId());
        holder.textViewTypes.setText(cards.get(position).getTypesString());
        holder.textViewSeries.setText(cards.get(position).getSet().getSeries());
        holder.textViewClass.setText(cards.get(position).getSupertype());
        Picasso.get().load(cards.get(position).images.getSmallImg()).into(holder.imageViewCardResult);
    }

    @Override
    public int getItemCount() {
        return cards.size();
    }

    public void updateCardList(List<Card> newCardList) { cards = newCardList; }
}
