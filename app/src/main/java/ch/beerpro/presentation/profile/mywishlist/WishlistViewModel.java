package ch.beerpro.presentation.profile.mywishlist;

import android.util.Pair;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import ch.beerpro.data.repositories.BeersRepository;
import ch.beerpro.data.repositories.CurrentUser;
import ch.beerpro.data.repositories.FridgeRepository;
import ch.beerpro.data.repositories.WishlistRepository;
import ch.beerpro.domain.models.Beer;
import ch.beerpro.domain.models.FridgeItem;
import ch.beerpro.domain.models.MyBeer;
import ch.beerpro.domain.models.MyBeerFromFridge;
import ch.beerpro.domain.models.Wish;
import ch.beerpro.presentation.profile.BasicFridgeViewModel;

import com.google.android.gms.tasks.Task;

import java.util.List;

public class WishlistViewModel extends BasicFridgeViewModel implements CurrentUser {

    private static final String TAG = "WishlistViewModel";

    private final WishlistRepository wishlistRepository;
    private final FridgeRepository fridgeRepository;
    private final BeersRepository beersRepository;

    public WishlistViewModel() {
        wishlistRepository = new WishlistRepository();
        beersRepository = new BeersRepository();
        fridgeRepository = new FridgeRepository();

        currentUserId.setValue(getCurrentUser().getUid());
    }

    public LiveData<List<Pair<Wish, MyBeer>>> getMyWishlistWithBeers() {
        return wishlistRepository.getMyWishlistWithBeers(currentUserId, fridgeRepository.getMyFridgeItems(currentUserId), beersRepository.getAllBeers());
    }

    public Task<Void> toggleItemInWishlist(String itemId) {
        return wishlistRepository.toggleUserWishlistItem(getCurrentUser().getUid(), itemId);
    }

}