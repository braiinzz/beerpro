package ch.beerpro.presentation.profile.mywishlist;

import android.widget.ImageView;
import ch.beerpro.domain.models.Beer;
import ch.beerpro.presentation.utils.OnDefaultBeerInteractionListener;
import ch.beerpro.presentation.utils.OnFridgeInteractionListener;

public interface OnWishlistItemInteractionListener extends OnFridgeInteractionListener {
    void onFridgeAddNewClickedListener(Beer item);
    void onWishClickedListener(Beer beer);
}
