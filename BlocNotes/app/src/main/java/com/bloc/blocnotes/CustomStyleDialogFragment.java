package com.bloc.blocnotes;

import android.app.DialogFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;

/**
 * Created by Daniel on 10/13/2014.
 */
public class CustomStyleDialogFragment extends DialogFragment{

    public static final String TAG = ".CustomStyleDialogFragment";

    // holds all the objects to be notified of changes
    private ArrayList<CustomStyleInterface> mObservers;

    Button mSmallButton;
    Button mMediumButton;
    Button mLargeButton;



    public CustomStyleDialogFragment() {
        // creates array to hold mObservers
        mObservers = new ArrayList<CustomStyleInterface>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle bundle) {
        Log.d(TAG, "entered onCreateView()");
        View view = inflater.inflate(R.layout.dialog_custom_style, container, false);
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
                String[] fonts = getResources().getStringArray(R.array.customize_fonts);
                String font = fonts[parent.getSelectedItemPosition()];
                Log.d(TAG, "font: " + font);
                changeFont(font);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // do nothing
            }
        });

        // sets up buttons
        mSmallButton = (Button) view.findViewById(R.id.btn_font_small);
        mSmallButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeStyle(14);
            }
        });

        mMediumButton = (Button) view.findViewById(R.id.btn_font_medium);
        mMediumButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeStyle(18);
            }
        });

        mLargeButton = (Button) view.findViewById(R.id.btn_font_large);
        mLargeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeStyle(22);
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

    /*
    * Interface that all activities hosting this fragment must implement
     */
    public static interface CustomStyleInterface {

        public void onStyleChange(CustomStyleDialogFragment dialog, int styleid);

        public void onFontChange(CustomStyleDialogFragment dialog, String fontName);

        public void onThemeChange(CustomStyleDialogFragment dialog, int themeId);
    }
}
