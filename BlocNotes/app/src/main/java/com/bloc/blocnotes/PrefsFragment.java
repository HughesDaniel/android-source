package com.bloc.blocnotes;

import android.os.Bundle;
import android.preference.PreferenceFragment;

/**
 * Created by Daniel on 10/15/2014.
 */
public class PrefsFragment extends PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load the preferences XML resource
        addPreferencesFromResource(R.xml.preferences);
    }
}
