package com.bloc.blocnotes.com.bloc.database.tables;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Daniel on 10/15/2014.
 */
public abstract class Table {

    private String TABLE_NAME;

    public Table(String name) {
        this.TABLE_NAME = name;
    }

    public String getName() {
        return this.TABLE_NAME;
    }



    /*
     * Return the create statement for this table
     */
    public abstract String getCreateStatement();

    /*
     * Upgrade the table
     */
    public abstract void onUpgrade(SQLiteDatabase sqlLiteDatabase, int oldVersion, int NewVersion);
}
