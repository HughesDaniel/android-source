package com.bloc.blocnotes;

import android.app.Activity;
import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daniel on 10/17/2014.
 */
public class NotesDisplayFragment extends Fragment {

    // Interface hosting activity must implement
    public interface CreateNewNote {
        public void onCreateNewNote(NotesDisplayFragment fragment);
    }

    // Where we will store the activity that is hosting this fragment
    private CreateNewNote mCallbacks;

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
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mCallbacks = (CreateNewNote) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException("Activity must implement CreateNewNote ");
        }
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        LinearLayout rootView = (LinearLayout) inflater.inflate(R.layout.notes_list,
                container, false);

        mListView = (ListView) rootView.findViewById(R.id.lv_note_list);

        // The view we will use to display when there are no notes in the adapter
        LinearLayout empty = (LinearLayout) rootView.findViewById(R.id.LL_empty_note_list);
        // Button in the empty view we will use to create a new note
        ImageButton imgBtn = (ImageButton) rootView.findViewById(R.id.ib_empty_note_list);
        imgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO ask how to fix this
                mCallbacks.onCreateNewNote();
            }
        });


        mListView.setEmptyView(empty);

        mArrayAdapter = new ArrayAdapter(getActivity(), R.layout.notebook_note,
                R.id.tv_notebook_note,
                mNotesList);

        mListView.setAdapter(mArrayAdapter);

        return rootView;
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
            if (mArrayAdapter != null) { // Adapter has been created, we need to update it
                mArrayAdapter.notifyDataSetChanged();
            }
        }
    }
}
