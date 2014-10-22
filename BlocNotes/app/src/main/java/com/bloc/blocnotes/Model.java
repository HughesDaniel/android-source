package com.bloc.blocnotes;

import com.bloc.blocnotes.com.bloc.database.BlocNotesDbHelper;

/**
 * Created by Daniel on 10/21/2014.
 */
public abstract class Model {

   // The unique ID of each model
    protected long mRowId;

    // The name of the database table
    protected String mTableName;

    // Our database we will acess
    protected BlocNotesDbHelper dbHelper = BlocNotesApplication.getBlocDb();

    public long getRowId() {
        return mRowId;
    }

    protected abstract boolean doesExist();
    protected abstract void createEntry();
}
