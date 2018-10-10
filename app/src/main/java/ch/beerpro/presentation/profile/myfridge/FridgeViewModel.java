package ch.beerpro.presentation.profile.myfridge;

import android.util.Pair;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import ch.beerpro.data.repositories.BeersRepository;
import ch.beerpro.data.repositories.CurrentUser;
import ch.beerpro.data.repositories.FridgeRepository;
import ch.beerpro.domain.models.Beer;
import ch.beerpro.domain.models.FridgeItem;

public class FridgeViewModel extends ViewModel implements CurrentUser {
    private final MutableLiveData<String> currentUserId = new MutableLiveData<>();
    private final BeersRepository beersRepository;
    private final FridgeRepository fridgeRepository;
    public FridgeViewModel() {
        beersRepository = new BeersRepository();
        fridgeRepository = new FridgeRepository();
        currentUserId.setValue(getCurrentUser().getUid());
    }

    public LiveData<List<Pair<FridgeItem, Beer>>> getMyFridgeListWithBeer() {
        LiveData<List<Beer>> allBeers = beersRepository.getAllBeers();
        return fridgeRepository.getMyFridgeWithBeers(currentUserId, allBeers);
    }
}
