package ch.beerpro.presentation;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import ch.beerpro.presentation.utils.DarkModeUtil;

public abstract class BaseActivity extends AppCompatActivity {

    boolean createdWithDarkMode = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createdWithDarkMode = DarkModeUtil.isEnabled();
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (createdWithDarkMode != DarkModeUtil.isEnabled()) {
            recreate();
        }
    }
}
