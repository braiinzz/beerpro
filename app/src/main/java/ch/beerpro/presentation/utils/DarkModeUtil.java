package ch.beerpro.presentation.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import androidx.appcompat.app.AppCompatDelegate;
import ch.beerpro.R;

public class DarkModeUtil {
    public static void applyPreference(Context context) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        boolean enabled = sharedPref.getBoolean(context.getString(R.string.pref_dark_mode_key), false);

        int nightMode = enabled ? AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO;
        AppCompatDelegate.setDefaultNightMode(nightMode);
    }

    public static boolean isEnabled() {
        return AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES;
    }
}
