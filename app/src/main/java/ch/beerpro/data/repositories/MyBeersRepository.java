package ch.beerpro.data.repositories;

import androidx.lifecycle.LiveData;
import ch.beerpro.domain.models.Beer;
import ch.beerpro.domain.models.Entity;
import ch.beerpro.domain.models.Rating;
import ch.beerpro.domain.models.Wish;
import ch.beerpro.presentation.profile.mybeers.models.MyBeer;
import ch.beerpro.presentation.profile.mybeers.models.MyBeerFromRating;
import ch.beerpro.presentation.profile.mybeers.models.MyBeerFromWishlist;
import org.apache.commons.lang3.tuple.Triple;

import java.util.*;

import static androidx.lifecycle.Transformations.map;
import static ch.beerpro.domain.utils.LiveDataExtensions.combineLatest;

public class MyBeersRepository {

    private static List<MyBeer> getMyBeers(Triple<List<Wish>, List<Rating>, HashMap<String, Beer>> input) {
        List<Wish> wishlist = input.getLeft();
        List<Rating> ratings = input.getMiddle();
        HashMap<String, Beer> beers = input.getRight();

        ArrayList<MyBeer> result = new ArrayList<>();
        Set<String> beersAlreadyOnTheList = new HashSet<>();
        for (Wish wish : wishlist) {
            String beerId = wish.getBeerId();
            result.add(new MyBeerFromWishlist(wish, beers.get(beerId)));
            beersAlreadyOnTheList.add(beerId);
        }

        for (Rating rating : ratings) {
            String beerId = rating.getBeerId();
            if (beersAlreadyOnTheList.contains(beerId)) {
                // if the beer is already on the wish list, don't add it again
            } else {
                result.add(new MyBeerFromRating(rating, beers.get(beerId)));
                // we also don't want to see a rated beer twice
                beersAlreadyOnTheList.add(beerId);
            }
        }
        Collections.sort(result, (r1, r2) -> r2.getDate().compareTo(r1.getDate()));
        return result;
    }


    public LiveData<List<MyBeer>> getMyBeers(LiveData<List<Beer>> allBeers, LiveData<List<Wish>> myWishlist,
                                             LiveData<List<Rating>> myRatings) {
        return map(combineLatest(myWishlist, myRatings, map(allBeers, Entity::entitiesById)),
                MyBeersRepository::getMyBeers);
    }

}
