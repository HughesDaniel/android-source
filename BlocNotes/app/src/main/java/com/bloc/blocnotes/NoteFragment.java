package com.bloc.blocnotes;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

/**
 * Created by Daniel on 10/9/2014.
 */
public class NoteFragment extends Fragment {

    private static final String TAG = ".NoteFragment.java";
    private static final String STRING_KEY = "com.bloc.blocnotes.notefragment.stringkey";

    EditText editText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle bundle) {
        Log.d(TAG, "entered onCreateView()");
        View rootView = inflater.inflate(R.layout.fragment_note, container, false);
        editText = (EditText) rootView.findViewById(R.id.et_note);

        if (bundle != null && bundle.getString(STRING_KEY) != null) {
            String s = bundle.getString(STRING_KEY);
            editText.setText(s);
        }
        // update UI based on users past preference selections
        getUserPrefs();

         return rootView;
    }

    @Override
    public void  onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // saves the text in the editor on destruction of fragment
        String s = editText.getText().toString();
        outState.putString(STRING_KEY, s);
    }

    // helper method that restores user preferences on creation of fragment
    private void getUserPrefs() {
        String text_size = getString(R.string.prefs_key_text_size);
        String font = getString(R.string.prefs_key_font);

        SharedPreferences prefs = getActivity().getPreferences(Context.MODE_PRIVATE);
        setStyle(prefs.getInt(text_size, android.R.style.TextAppearance_DeviceDefault_Medium));
        setFont(prefs.getString(font, null));
    }

    /*
    * used to change the font of the editText field
     */
    public void setFont(String newFont) {
        if (newFont == null) {
            return;
        }
        Log.d(TAG, "setFont: " + newFont);
        try {
            Typeface font = Typeface.createFromAsset(getActivity().getAssets(), "/fonts/" + newFont);
            editText.setTypeface(font);
        } catch (Exception e) {
            Log.d(TAG, "exception in setFont(): " + e.toString());
        }
    }

    /*
     used to change the size of the text in the editText field
     */
    public void setStyle(int size) {
        editText.setTextAppearance(getActivity(), size);
    }

}

