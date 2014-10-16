package com.bloc.blocnotes.com.bloc.database.tables;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Daniel on 10/15/2014.
 */
public class NotesTable extends Table {

    public NotesTable(String name) {
        super(name);
    }

    @Override
    public String getCreateStatement() {
        String createStatement = "CREATE TABLE Notes (" +
                "_id INTEGER PRIMARY KEY," +
                "body TEXT" +
                "notebook INTEGER" +
                " )";
        return createStatement;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqlLiteDatabase, int oldVersion, int NewVersion) {

    }
}
