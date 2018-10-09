package ch.beerpro.presentation.profile.myfridge;

import android.util.Pair;

import org.apache.commons.lang3.NotImplementedException;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import ch.beerpro.data.repositories.BeersRepository;
import ch.beerpro.data.repositories.CurrentUser;
import ch.beerpro.data.repositories.WishlistRepository;
import ch.beerpro.domain.models.Beer;
import ch.beerpro.domain.models.FridgeItem;

import static androidx.lifecycle.Transformations.map;

public class FridgeViewModel extends ViewModel implements CurrentUser {
    private final MutableLiveData<String> currentUserId = new MutableLiveData<>();
    private final WishlistRepository wishlistRepository;
    private final BeersRepository beersRepository;
    public FridgeViewModel() {
        wishlistRepository = new WishlistRepository();
        beersRepository = new BeersRepository();
        currentUserId.setValue(getCurrentUser().getUid());
    }

    public LiveData<List<Pair<FridgeItem, Beer>>> getMyFridgeListWithBeer() {
        return map(beersRepository.getAllBeers(), input -> {
            ArrayList<Pair<FridgeItem, Beer>> result = new ArrayList<>(input.size());

            for(Beer item : input) {
                result.add(Pair.create(new FridgeItem(), item));
            }
            return result;
        });
    }
}
