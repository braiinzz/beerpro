package ch.beerpro.presentation.profile.myfridge;

import android.widget.ImageView;

import ch.beerpro.domain.models.Beer;

interface OnFridgelistInteractionListener {
    void onMoreClickedListener(ImageView photo, Beer beer);
}
