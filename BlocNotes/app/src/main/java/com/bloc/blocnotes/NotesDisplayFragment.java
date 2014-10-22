package com.bloc.blocnotes;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daniel on 10/17/2014.
 */
public class NotesDisplayFragment extends Fragment {

    private static final String TAG = ".NotesDisplayFragment.java";

    private static final String LONG_BUNDLE_KEY = "com.bloc.blocnotes.NotesDisplayFragment." +
            "long_bunlde_key";

    private ListView mListView;
    private ArrayAdapter mArrayAdapter;

    // The list of notes we will give the adapter for display
    private List<String> mNotesList = new ArrayList<String>();

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

        // this requires read access, so we do it in a separate thread
        new PopulateNotesList().execute();
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       mListView = (ListView) inflater.inflate(R.layout.listview_fragment, container, false);

       mArrayAdapter = new ArrayAdapter(getActivity(), R.layout.notebook_note,
                R.id.tv_notebook_note,
                mNotesList);

        mListView.setAdapter(mArrayAdapter);

        return mListView;
    }


    public class PopulateNotesList extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            mNotesList = new NotesCenter().getNotesFromNotebook(mNotebookId);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (mArrayAdapter != null) { // Apdater has been created, we need to update it
                mArrayAdapter.notifyDataSetChanged();
            }
        }
    }
}
