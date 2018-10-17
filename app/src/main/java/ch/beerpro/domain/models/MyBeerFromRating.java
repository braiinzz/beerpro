package ch.beerpro.domain.models;

import lombok.Data;

import java.util.Date;

@Data
public class MyBeerFromRating implements MyBeer {
    private Rating rating;
    private Beer beer;
    private FridgeItem fridgeItem;

    public MyBeerFromRating(Rating rating, Beer beer) {
        this.rating = rating;
        this.beer = beer;
    }

    @Override
    public String getBeerId() {
        return rating.getBeerId();
    }

    @Override
    public Date getDate() {
        return rating.getCreationDate();
    }
}
