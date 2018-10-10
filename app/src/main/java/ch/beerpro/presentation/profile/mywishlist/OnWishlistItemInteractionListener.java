package ch.beerpro.presentation.profile.mywishlist;

import android.widget.ImageView;
import ch.beerpro.domain.models.Beer;
import ch.beerpro.presentation.utils.OnDefaultBeerInteractionListener;

public interface OnWishlistItemInteractionListener extends OnDefaultBeerInteractionListener {

    void onWishClickedListener(Beer beer);
}
