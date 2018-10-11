package ch.beerpro.presentation.profile.myfridge;

import android.widget.ImageView;

import ch.beerpro.domain.models.Beer;
import ch.beerpro.domain.models.FridgeItem;
import ch.beerpro.presentation.utils.OnDefaultBeerInteractionListener;

interface OnFridgeInteractionListener extends OnDefaultBeerInteractionListener {
    void onAddClickedListener(FridgeItem fridgeItem);
    void onRemoveClickedListener(FridgeItem fridgeItem);
}
