package com.bloc.blocnotes;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

/**
 * Created by Daniel on 10/9/2014.
 */
public class NoteFragment extends Fragment {

    private static final String TAG = ".NoteFragment.java";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceSTate) {

        Log.d(TAG, "entered onCreateView()");

        Context context = getActivity().getApplicationContext();

        LinearLayout linearLayout = new LinearLayout(context);
        EditText editText = new EditText(context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        linearLayout.addView(editText);


        return inflater.inflate(R.layout.fragment_note, container, false);
    }


}

