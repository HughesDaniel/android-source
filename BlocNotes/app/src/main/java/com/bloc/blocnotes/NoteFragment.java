package com.bloc.blocnotes;

import android.app.Fragment;
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

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle bundle) {
        Log.d(TAG, "entered onCreateView()");

        if (bundle != null && bundle.getString(STRING_KEY) != null) {
            String s = bundle.getString(STRING_KEY);
            Log.d(TAG, "savedinstance state wasnt null: " + s);
        }

        editText = (EditText) getActivity().findViewById(R.id.et_note);



        return inflater.inflate(R.layout.fragment_note, container, false);
    }

    @Override
    public void  onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        String s = editText.getText().toString();
        Log.d(TAG, s);
        outState.putString(STRING_KEY, s);
    }

}

