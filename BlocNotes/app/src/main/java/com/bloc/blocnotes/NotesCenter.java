package com.bloc.blocnotes;

import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daniel on 10/22/2014.
 */
public class NotesCenter extends ModelCenter {

    private static final String TAG = ".NotesCenter.java";

    // Hardcoded table name
    private static final String TABLE_NAME = "Notes";

    // Names of the columns in the Notes table in our DB
    private static final String COL_BODY = "body";
    private static final String COL_NOTEBOOK = "notebook";

    public NotesCenter() {
        super(TABLE_NAME);
    }

    @Override
    protected Model _createModel() {
        return null;
    }

    public List<String> getNotesFromNotebook(long notebookId) {

        List<String> notes = new ArrayList<String>();

        Cursor names = BlocNotesApplication.getBlocDb().getReadableDatabase()
                .query(true, mTableName, new String[] {COL_NOTEBOOK, COL_BODY},
                        null, null, null, null, null, null);

        int notebookColumnIndex = names.getColumnIndex(COL_NOTEBOOK); // int for the notebook col
        int bodyColumnIndex = names.getColumnIndex(COL_BODY); // int for the body column

        while(names.moveToNext()) {
            // checkes if the notebook user selected is the same as the current cursor position
            if (notebookColumnIndex == notebookId) {
                // adds it to the list if it is
                notes.add(names.getString(bodyColumnIndex));
            }
        }

        names.close();

        return notes;
    }
}
