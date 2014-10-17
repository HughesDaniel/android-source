package com.bloc.blocnotes.com.bloc.database.tables;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Daniel on 10/15/2014.
 */
public class NotebookTable extends Table {

    public NotebookTable(String name) {
        super(name);
    }

    @Override
    public String getCreateStatement() {
        String createStatement = "CREATE TABLE Notebook (" +
                "_id INTEGER PRIMARY KEY," +
                "name TEXT" +
                " )";

        return createStatement;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqlLiteDatabase, int oldVersion, int NewVersion) {

    }
}
