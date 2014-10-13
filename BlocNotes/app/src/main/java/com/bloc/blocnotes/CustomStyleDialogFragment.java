package com.bloc.blocnotes;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Daniel on 10/13/2014.
 */
public class CustomStyleDialogFragment extends Fragment{

    public static final String TAG = ".CustomStyleDialogFragment";

    public CustomStyleDialogFragment() {
        // required empty constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle bundle) {
        Log.d(TAG, "entered onCreateView()");

        return inflater.inflate(R.layout.dialog_custom_style, container, false);
    }

    /*
    * Interface that all activities hosting this fragment must implement
     */
    public static interface CustomStyleInterface {

        public void onStyleChange(CustomStyleDialogFragment dialog, int styleid);

        public void onFontChange(CustomStyleDialogFragment dialog, String fontName);

        public void onThemeChange(CustomStyleDialogFragment dialog, int themeId);
    }
}
