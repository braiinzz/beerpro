package ch.beerpro.presentation.profile.mybeers;

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
import ch.beerpro.domain.models.Beer;
import ch.beerpro.domain.models.MyBeer;
import ch.beerpro.domain.models.MyBeerFromRating;
import ch.beerpro.domain.models.MyBeerFromWishlist;
import ch.beerpro.presentation.utils.DefaultBeerViewHolder;
import ch.beerpro.presentation.utils.DrawableHelpers;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.firebase.auth.FirebaseUser;

import java.text.DateFormat;


public class MyBeersRecyclerViewAdapter extends ListAdapter<MyBeer, MyBeersRecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "MyBeersRecyclerViewAdap";

    private static final DiffUtil.ItemCallback<MyBeer> DIFF_CALLBACK = new DiffUtil.ItemCallback<MyBeer>() {
        @Override
        public boolean areItemsTheSame(@NonNull MyBeer oldItem, @NonNull MyBeer newItem) {
            return oldItem.getBeerId().equals(newItem.getBeerId());
        }

        @Override
        public boolean areContentsTheSame(@NonNull MyBeer oldItem, @NonNull MyBeer newItem) {
            return oldItem.equals(newItem);
        }
    };

    private final OnMyBeerItemInteractionListener listener;
    private FirebaseUser user;

    public MyBeersRecyclerViewAdapter(OnMyBeerItemInteractionListener listener, FirebaseUser user) {
        super(DIFF_CALLBACK);
        this.listener = listener;
        this.user = user;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.activity_my_beers_listentry, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        MyBeer entry = getItem(position);
        holder.bind(entry, listener);
    }

    class ViewHolder extends DefaultBeerViewHolder {
        @BindView(R.id.addedAt)
        TextView addedAt;

        @BindView(R.id.onTheListSince)
        TextView onTheListSince;

        @BindView(R.id.removeFromWishlist)
        Button removeFromWishlist;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, itemView);
        }

        public void bind(MyBeer entry, OnMyBeerItemInteractionListener listener) {

            Beer item = entry.getBeer();
            super.bind(item, listener);
            removeFromWishlist.setOnClickListener(v -> listener.onWishClickedListener(item));

            String formattedDate =
                    DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.SHORT).format(entry.getDate());
            addedAt.setText(formattedDate);

            if (entry instanceof MyBeerFromWishlist) {
                DrawableHelpers
                        .setDrawableTint(removeFromWishlist, itemView.getResources().getColor(R.color.colorPrimary));
                onTheListSince.setText("auf der Wunschliste seit");
            } else if (entry instanceof MyBeerFromRating) {
                DrawableHelpers.setDrawableTint(removeFromWishlist,
                        itemView.getResources().getColor(android.R.color.darker_gray));
                removeFromWishlist.setText("Wunschliste");
                onTheListSince.setText("beurteilt am");
            }
        }
    }
}
