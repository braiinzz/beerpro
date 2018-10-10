package ch.beerpro.presentation.profile.mywishlist;

import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import ch.beerpro.GlideApp;
import ch.beerpro.R;
import ch.beerpro.presentation.utils.DefaultBeerViewHolder;
import ch.beerpro.presentation.utils.EntityPairDiffItemCallback;
import ch.beerpro.domain.models.Beer;
import ch.beerpro.domain.models.Wish;
import ch.beerpro.presentation.utils.OnDefaultBeerInteractionListener;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.text.DateFormat;


public class WishlistRecyclerViewAdapter extends ListAdapter<Pair<Wish, Beer>, WishlistRecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "WishlistRecyclerViewAda";

    private static final DiffUtil.ItemCallback<Pair<Wish, Beer>> DIFF_CALLBACK = new EntityPairDiffItemCallback<>();

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
        Pair<Wish, Beer> item = getItem(position);
        holder.bind(item.first, item.second, listener);
    }

    class ViewHolder extends DefaultBeerViewHolder {
        @BindView(R.id.addedAt)
        TextView addedAt;

        @BindView(R.id.removeFromWishlist)
        Button remove;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, itemView);
        }

        void bind(Wish wish, Beer item, OnWishlistItemInteractionListener listener) {
            super.bind(item, listener);
            String formattedDate =
                    DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.SHORT).format(wish.getAddedAt());
            addedAt.setText(formattedDate);
            remove.setOnClickListener(v -> listener.onWishClickedListener(item));
        }

    }
}
