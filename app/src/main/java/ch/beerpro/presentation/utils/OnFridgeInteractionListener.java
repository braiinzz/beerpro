package ch.beerpro.presentation.utils;

import android.widget.ImageView;

import ch.beerpro.domain.models.Beer;
import ch.beerpro.domain.models.FridgeItem;
import ch.beerpro.presentation.utils.OnDefaultBeerInteractionListener;

public interface OnFridgeInteractionListener extends OnDefaultBeerInteractionListener {
    void onFridgeAddClickedListener(FridgeItem fridgeItem);
    void onFridgeRemoveClickedListener(FridgeItem fridgeItem);
}
