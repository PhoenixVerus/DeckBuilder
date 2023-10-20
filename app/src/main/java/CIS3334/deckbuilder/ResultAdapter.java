package CIS3334.deckbuilder;

import android.app.Application;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to send information to the results view holder
 */
public class ResultAdapter extends RecyclerView.Adapter<ResultViewHolder> {

    private Application application;
    private MainViewModel mainViewModel;
    private List<Card> cards;

    public ResultAdapter(Application application, MainViewModel mainViewModel) {
        this.application = application;
        this.mainViewModel = mainViewModel;
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
        //holder.textViewTypes.setText(cards.get(position).getTypes());
    }

    @Override
    public int getItemCount() {
        return cards.size();
    }

    public void updateCardList(List<Card> newCardList) { cards = newCardList; }
}
