package com.bloc.blocnotes;

import android.app.DialogFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

/**
 * Created by Daniel on 10/13/2014.
 */
public class CustomStyleDialogFragment extends DialogFragment{

    public static final String TAG = ".CustomStyleDialogFragment";

    public CustomStyleDialogFragment() {
        // required empty constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle bundle) {
        Log.d(TAG, "entered onCreateView()");
        View view = inflater.inflate(R.layout.dialog_custom_style, container, false);
        getDialog().setTitle(R.string.customize);

        // set up and create the system font spinner
        Spinner spinner = (Spinner) view.findViewById(R.id.system_font_spinner);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, new String[] {"System Font", "Helvetica",
                "Helvetica-Neue", "Impact"});
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);

        return view;
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
