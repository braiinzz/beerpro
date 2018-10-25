package ch.beerpro;

import android.app.Application;
import android.preference.PreferenceManager;

import com.google.firebase.firestore.FirebaseFirestore;

import ch.beerpro.presentation.utils.DarkModeUtil;

/**
 * The MyApplication class is instantiated once for the whole application and can (but should not) be used as a
 * singleton to store application wide data. The manifest contains a reference to this class, apart from that it is
 * not referenced in this application.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        PreferenceManager.setDefaultValues(this, R.xml.preferences, false);

        DarkModeUtil.applyPreference(this);

        /*
         * This will log whenever we reveice data from firestore. This is useful for debugging and to get a feeling
         * of how much and when new data is received from the database.
         * */
        FirebaseFirestore.setLoggingEnabled(true);
    }
}