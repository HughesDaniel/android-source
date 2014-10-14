package com.bloc.blocnotes;

import android.app.Fragment;
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

         return rootView;
    }

    @Override
    public void  onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        String s = editText.getText().toString();
        outState.putString(STRING_KEY, s);
    }

    /*
    * used to change the font of the editText field
     */
    public void setFont(String newFont) {
        try {
            Typeface font = Typeface.createFromAsset(getActivity().getAssets(), newFont);
            editText.setTypeface(font);
        } catch (Exception e) {
            Log.d(TAG, "exception in setFont(): " + e.toString());
        }
    }

}

