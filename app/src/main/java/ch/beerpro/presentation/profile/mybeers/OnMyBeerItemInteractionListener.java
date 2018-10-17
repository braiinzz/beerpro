package ch.beerpro.presentation.profile.mybeers;

import ch.beerpro.domain.models.Beer;
import ch.beerpro.domain.models.FridgeItem;
import ch.beerpro.presentation.utils.OnDefaultBeerInteractionListener;
import ch.beerpro.presentation.utils.OnFridgeInteractionListener;

public interface OnMyBeerItemInteractionListener extends OnFridgeInteractionListener {
    void onAddNewClickedListener(Beer item);
    void onWishClickedListener(Beer item);
}
