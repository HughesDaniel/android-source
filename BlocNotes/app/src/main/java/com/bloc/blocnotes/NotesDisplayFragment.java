package com.bloc.blocnotes;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by Daniel on 10/17/2014.
 */
public class NotesDisplayFragment extends Fragment {

    private static final String TAG = ".NotesDisplayFragment.java";

    private static final String LONG_BUNDLE_KEY = "com.bloc.blocnotes.NotesDisplayFragment." +
            "long_bunlde_key";

    private ListView mListView;

    // The id of the notebook the user selects
    private long mNotebookId;

    public static NotesDisplayFragment newInstance(long aLong) {

        NotesDisplayFragment fragment = new NotesDisplayFragment();

        Bundle args = new Bundle();
        args.putLong(LONG_BUNDLE_KEY, aLong);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle SavedInstanceState) {
        super.onCreate(SavedInstanceState);

        mNotebookId = getArguments().getLong(LONG_BUNDLE_KEY);
        Log.d(TAG, mNotebookId + "");
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       mListView = (ListView) inflater.inflate(R.layout.listview_fragment, container, false);

        mListView.setAdapter(new ArrayAdapter(getActivity(), R.layout.notebook_note,
                R.id.tv_notebook_note,
                new NotesCenter().getNotesFromNotebook(mNotebookId)));

        return mListView;
    }
}
