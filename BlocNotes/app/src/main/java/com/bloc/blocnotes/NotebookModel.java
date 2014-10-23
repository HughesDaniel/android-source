package com.bloc.blocnotes;

import android.content.ContentValues;
import android.database.Cursor;

/**
 * Created by Daniel on 10/21/2014.
 */
public class NotebookModel extends Model {

    private static final String TAG = ".NotebookModel.java";

    // Column in table that holds the name
    private static final String COL_NAME = "name";
    // Column in table that holds the unique ID
    private static final String COL_ID = "_id";

    // The name of the notebook
    private String mName;


    public NotebookModel(String name) {
        mName = name;
        mTableName = "Notebook";

        if (!doesExist()) { // tests to see if 'name' exists in database
            createEntry(); // creates it if not
        }

        mRowId = setRowId(name);
    }

    // Returns a boolean that is the status of whether 'name' exists in the DB
    @Override
    protected boolean doesExist() {
        String query = "SELECT * FROM " + mTableName + " WHERE " + COL_NAME +
                "=?";
        Cursor cursor = dbHelper.getReadableDatabase().rawQuery(query, new String[] {mName});
        boolean value = (cursor.getCount() > 0);
        cursor.close();

        return value;
    }

    // Creates a new notebook in the Notebook table with the given name
    @Override
    protected void createEntry() {
        ContentValues values = new ContentValues();
        values.put(COL_NAME, mName);
        dbHelper.getWritableDatabase().insert(mTableName, null, values);

    }

    private long setRowId(String name){
        String query = "Select * from " + mTableName + " where " + COL_NAME +
                "=?";
        Cursor cursor = dbHelper.getReadableDatabase().rawQuery(query, new String[] {name});
        cursor.moveToFirst();
        long rowId = cursor.getLong(cursor.getColumnIndex(COL_ID));
        cursor.close();

        return rowId;
    }

    public String getName() {
        return mName;
    }
}


