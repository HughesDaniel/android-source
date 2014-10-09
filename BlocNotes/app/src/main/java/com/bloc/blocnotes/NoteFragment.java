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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceSTate) {
        Log.d(TAG, "entered onCreateView()");

        EditText editText = (EditText) getActivity().findViewById(R.id.et_note);

        return inflater.inflate(R.layout.fragment_note, container, false);
    }


}

