package ch.beerpro.presentation.profile.mywishlist;

import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import ch.beerpro.R;
import ch.beerpro.domain.models.MyBeer;
import ch.beerpro.presentation.utils.EntityPairDiffItemCallback;
import ch.beerpro.domain.models.Wish;
import ch.beerpro.presentation.utils.FridgeBeerViewHolder;

import java.text.DateFormat;


public class WishlistRecyclerViewAdapter extends ListAdapter<Pair<Wish, MyBeer>, WishlistRecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "WishlistRecyclerViewAda";

    private static final DiffUtil.ItemCallback<Pair<Wish, MyBeer>> DIFF_CALLBACK = new EntityPairDiffItemCallback<>();

    private final OnWishlistItemInteractionListener listener;

    public WishlistRecyclerViewAdapter(OnWishlistItemInteractionListener listener) {
        super(DIFF_CALLBACK);
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.activity_my_wishlist_listentry, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        Pair<Wish, MyBeer> item = getItem(position);
        holder.bind(item.first, item.second, listener);
    }

    class ViewHolder extends FridgeBeerViewHolder {
        @BindView(R.id.addedAt)
        TextView addedAt;

        @BindView(R.id.removeFromWishlist)
        Button remove;

        @BindView(R.id.addToFridge)
        Button addToFridge;

        @BindView(R.id.removeFromFridge)
        Button removeFromFridge;

        @BindView(R.id.amount)
        TextView amount;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, itemView);
        }

        void bind(Wish wish, MyBeer item, OnWishlistItemInteractionListener listener) {
            if (item.getFridgeItem() != null) {
                super.bind(item.getFridgeItem(), item.getBeer(), listener);
            } else {
                super.bind(item.getBeer(), listener);
                addToFridge.setOnClickListener(v -> listener.onFridgeAddNewClickedListener(item.getBeer()));
                removeFromFridge.setVisibility(View.INVISIBLE);
                amount.setText("0 Biere");
            }

            String formattedDate =
                    DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.SHORT).format(wish.getAddedAt());
            addedAt.setText(formattedDate);
            remove.setOnClickListener(v -> listener.onWishClickedListener(item.getBeer()));
        }

    }
}
