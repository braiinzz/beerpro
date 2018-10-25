package ch.beerpro.presentation.profile.mybeers;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;
import butterknife.BindView;
import butterknife.ButterKnife;
import ch.beerpro.R;
import ch.beerpro.domain.models.Beer;
import ch.beerpro.domain.models.FridgeItem;
import ch.beerpro.presentation.BaseActivity;
import ch.beerpro.presentation.details.DetailsActivity;

public class MyBeersActivity extends BaseActivity implements OnMyBeerItemInteractionListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private MyBeersViewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_beers);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getString(R.string.title_activity_mybeers));

        model = ViewModelProviders.of(this).get(MyBeersViewModel.class);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_my_beers, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        searchItem.getIcon().setColorFilter(getColor(R.color.textToolbar), PorterDuff.Mode.SRC_ATOP);

        SearchView searchView = (SearchView) searchItem.getActionView();
        EditText searchEditText = searchView.findViewById(androidx.appcompat.R.id.search_src_text);
        searchEditText.setHintTextColor(getResources().getColor(R.color.textSubtle));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                model.setSearchTerm(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                model.setSearchTerm(null);
                return false;
            }
        });

        searchView.setOnCloseListener(() -> {
            model.setSearchTerm(null);
            return false;
        });

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                supportFinishAfterTransition();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onMoreClickedListener(ImageView photo, Beer item) {
        Intent intent = new Intent(this, DetailsActivity.class);
        intent.putExtra(DetailsActivity.ITEM_ID, item.getId());
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this, photo, "image");
        startActivity(intent, options.toBundle());
    }

    @Override
    public void onAddNewClickedListener(Beer item) {
        model.addToFridge(item);
    }

    @Override
    public void onWishClickedListener(Beer item) {
        model.toggleItemInWishlist(item.getId());
    }

    @Override
    public void onFridgeAddClickedListener(FridgeItem fridgeItem) {
        model.addToFridge(fridgeItem);
    }

    @Override
    public void onFridgeRemoveClickedListener(FridgeItem fridgeItem) {
        model.removeFromFridge(fridgeItem);
    }
}
