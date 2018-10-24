package ch.beerpro.presentation.details;

import ch.beerpro.domain.models.Beer;
import ch.beerpro.domain.models.Rating;

interface OnDetailInteractionListener {
    void onRatingLikedListener(Rating rating);
}
