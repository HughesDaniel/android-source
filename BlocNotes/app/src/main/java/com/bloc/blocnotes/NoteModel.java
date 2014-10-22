package com.bloc.blocnotes;

import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;

/**
 * Created by Daniel on 10/22/2014.
 */
public class NoteModel extends Model {

    // The body of our note
    private String mBody;

    // The notebook it belongs to, stored as an integer in the database
    private long mNotebook;

    // Column names in the notes table in our database
    private static final String COL_ID = "_id";
    private static final String COL_BODY = "body";
    private static final String COL_NOTEBOOK = "notebook";

    public NoteModel(String body, long notebook) {
        Log.d("DAFUQ", "im in notemodel");
        mTableName = "Notes";
        mBody = body;
        mNotebook = notebook;

        if (!doesExist()) {
            Log.d("DAFUQ", "it didnt exist!");
            createEntry();
        }

        mRowId = setRowId();
    }


    @Override
    protected boolean doesExist() {
        String query = "SELECT * FROM " + mTableName + " WHERE " + COL_BODY +
                "=?";
        Cursor cursor = dbHelper.getReadableDatabase().rawQuery(query, new String[] {mBody});
        boolean value = (cursor.getCount() > 0);
        cursor.close();

        return value;
    }

    @Override
    protected void createEntry() {
        ContentValues values = new ContentValues();
        values.put(COL_BODY, mBody);
        values.put(COL_NOTEBOOK, mNotebook);
        dbHelper.getWritableDatabase().insert(mTableName, null, values);
    }

    private long setRowId() {
        String query = "Select * from " + mTableName + " where " + COL_BODY +
                "=?";
        Cursor cursor = dbHelper.getReadableDatabase().rawQuery(query, new String[] {mBody});
        cursor.moveToFirst();
        long rowId = cursor.getLong(cursor.getColumnIndex(COL_ID));
        cursor.close();

        return rowId;
    }

    public String getBody() {
        return mBody;
    }

    public long getNotebook() {
        return mNotebook;
    }
}
