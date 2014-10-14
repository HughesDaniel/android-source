package com.bloc.blocnotes;

import android.app.Activity;
import android.app.DialogFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

/**
 * Created by Daniel on 10/13/2014.
 */
public class CustomStyleDialogFragment extends DialogFragment{

    public static final String TAG = ".CustomStyleDialogFragment";

    // holds all the objects to be notified of changes
    private ArrayList<CustomStyleInterface> observers;



    public CustomStyleDialogFragment() {
        // required empty constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle bundle) {
        Log.d(TAG, "entered onCreateView()");
        View view = inflater.inflate(R.layout.dialog_custom_style, container, false);
        getDialog().setTitle(R.string.customize);

        // creates array to hold observers
        observers = new ArrayList<CustomStyleInterface>();

        // set up and create the system font spinner
        Spinner spinner = (Spinner) view.findViewById(R.id.system_font_spinner);
        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.customize_fonts, android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);

        // gets the selection
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] fonts = getResources().getStringArray(R.array.customize_fonts);
                String font = fonts[parent.getSelectedItemPosition()];
                changeFont(font);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // do nothing
            }
        });

        return view;
    }

    public void  addListener(CustomStyleInterface listener) {
        Log.d(TAG, "Listener: " + listener + " added");
        observers.add(listener);
    }

    public void removeListener(CustomStyleInterface listener) {
        observers.remove(listener);
    }

    private void changeFont(String font) {
        for (CustomStyleInterface listener: observers) {
            listener.onFontChange(this, font);
        }
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
