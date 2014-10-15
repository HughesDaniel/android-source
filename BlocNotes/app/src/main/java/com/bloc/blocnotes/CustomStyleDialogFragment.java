package com.bloc.blocnotes;

import android.app.DialogFragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioGroup;
import android.widget.Spinner;

import java.util.ArrayList;

/**
 * Created by Daniel on 10/13/2014.
 */
public class CustomStyleDialogFragment extends DialogFragment{


    public static final String TAG = ".CustomStyleDialogFragment";

    // holds all the objects to be notified of changes
    private ArrayList<CustomStyleInterface> mObservers;

    RadioGroup mRadioGroupSize;

    public CustomStyleDialogFragment() {
        // creates array to hold observers
        mObservers = new ArrayList<CustomStyleInterface>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle bundle) {
        Log.d(TAG, "entered onCreateView()");
        final View view = inflater.inflate(R.layout.dialog_custom_style, container, false);
        getDialog().setTitle(R.string.customize);

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
                // The selection will be the font, which will be converted to proper file name
                String[] fonts = getResources().getStringArray(R.array.customize_fonts);
                String font = fonts[parent.getSelectedItemPosition()] + ".ttf";
                font = font.toLowerCase();
                Log.d(TAG, "font: " + font);
                changeFont(font);
                // update shared preferences
                String key = getResources().getString(R.string.prefs_key_font);
                updateSharedPrefs(key, font, null);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // do nothing
            }
        });

        // sets up buttons
        mRadioGroupSize = (RadioGroup) view.findViewById(R.id.rg_font_size);
        mRadioGroupSize.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int value = 0;
                if (checkedId == R.id.rb_small_font) {
                    value = android.R.style.TextAppearance_Small;
                    changeStyle(value);
                } else if (checkedId == R.id.rb_medium_font) {
                    value = android.R.style.TextAppearance_Medium;
                    changeStyle(value);
                } else if (checkedId == R.id.rb_large_font) {
                    value = android.R.style.TextAppearance_Large;
                    changeStyle(value);
                }
                // update shared preferences
                String key = getResources().getString(R.string.prefs_key_text_size);
                updateSharedPrefs(key, null, value);
            }
        });

        return view;
    }

    /*
    * method activities use to add themselves as a listener
     */
    public void  addListener(CustomStyleInterface listener) {
        mObservers.add(listener);
        Log.d(TAG, "Listener: " + listener + " added");
    }

    /*
    * method activities use to remove themselves as a listener
    */
    public void removeListener(CustomStyleInterface listener) {

        mObservers.remove(listener);
    }

    // helper method for telling listeners to change font
    private void changeFont(String font) {
        for (CustomStyleInterface listener: mObservers) {
            listener.onFontChange(this, font);
        }
    }

    // helper method for telling listeners to change style
    private void changeStyle(int size) {
        for (CustomStyleInterface listener: mObservers) {
            listener.onStyleChange(this, size);
        }
    }

    // helper method for updating shared preference file
    private void updateSharedPrefs(String key, String str_value, Integer int_value) {
        SharedPreferences.Editor editor = getActivity().getPreferences(
                Context.MODE_PRIVATE).edit();
        if (str_value != null) {
            editor.putString(key, str_value);
        } else if (int_value != null) {
            editor.putInt(key, int_value);
        }
        editor.commit();
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
