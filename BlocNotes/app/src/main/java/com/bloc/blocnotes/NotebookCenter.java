package com.bloc.blocnotes;

import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daniel on 10/21/2014.
 */
public class NotebookCenter extends ModelCenter<NotebookModel> {
    
    // The table name in our DB
    private static final String TABLE_NAME = "Notebook";

    // The column name in our DB
    private static final String COL_NAME = "name";


    public NotebookCenter() {
        super(TABLE_NAME);
    }

    @Override
    protected NotebookModel _createModel() {
        return null;
    }

    // returns an an arraylist of all the notebook names from our DB
    public List<String> getNotebookNames() {
        List<String> notebookNames = new ArrayList<String>();

        Cursor names = BlocNotesApplication.getBlocDb().getReadableDatabase()
                .query(true, mTableName, new String[] {COL_NAME},
        null, null, null, null, null, null);

        int columnIndex = names.getColumnIndex(COL_NAME);

        while(names.moveToNext()) {
            notebookNames.add(names.getString(columnIndex));
        }

        names.close();

        return notebookNames;
    }
}
