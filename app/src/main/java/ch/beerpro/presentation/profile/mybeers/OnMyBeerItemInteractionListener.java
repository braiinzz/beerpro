package ch.beerpro.presentation.profile.mybeers;

import ch.beerpro.domain.models.Beer;
import ch.beerpro.presentation.utils.OnDefaultBeerInteractionListener;

public interface OnMyBeerItemInteractionListener extends OnDefaultBeerInteractionListener {
    void onWishClickedListener(Beer item);
}
