package com.bloc.blocnotes;

import android.app.Fragment;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

/**
 * Created by Daniel on 10/17/2014.
 */
public class NotesDisplayFragment extends Fragment {

    private static final String TAG = ".NotesDisplayFragment.java";

    private ListView mListView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       mListView = (ListView) inflater.inflate(R.layout.listview_fragment, container, false);

        SQLiteDatabase db = BlocNotesApplication.getBlocDb()
                .getReadableDatabase();

        // gets a particular notebook from our notebook DB
        Cursor cursor = db.query("Notebook",
                new String[] {"_id"},
                "name IS ?",
                new String[] {"Uncategorized"}, // the particular notebook
                null,
                null,
                null,
                "1"
                );

        if (!cursor.moveToFirst()) {
            //notebook isn't found, throw an exception
            throw new NullPointerException("notebook not found");
        }

        // grabs the row we moved cursor to from above, should be our notebook
        long uncategorizedRowId = cursor.getLong(cursor.getColumnIndex("_id"));

        //gets all the info for
        Cursor notebookCursor = db.query("Notes",
                null,
                "notebook == ?",
                new String[] {String.valueOf(uncategorizedRowId)},
                null, null, null, null);

        // create the adapter
        SimpleCursorAdapter cursorAdapter =
                new SimpleCursorAdapter(getActivity(),
                        R.layout.notebook_note,
                        notebookCursor,
                        new String[] {"body"},
                        new int[] {R.id.tv_notebook_note}, 0);

        mListView.setAdapter(cursorAdapter);

        // close the db
        db.close();

        return mListView;
    }
}
