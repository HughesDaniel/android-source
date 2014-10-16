package com.bloc.blocnotes.com.bloc.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.bloc.blocnotes.com.bloc.database.tables.NotebookTable;
import com.bloc.blocnotes.com.bloc.database.tables.NotesTable;
import com.bloc.blocnotes.com.bloc.database.tables.Table;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by Daniel on 10/15/2014.
 */
public class BlocNotesDbHelper extends SQLiteOpenHelper {

    // Version
    private static final int DATABASE_VERSION = 1;
    // Name
    private static final String DATABASE_NAME = "BlocNotes";
    // Tables
    private static Set<Table> sTables = new HashSet<Table>();
    static {
        sTables.add(new NotebookTable("Notebook"));
        sTables.add(new NotesTable("Notes"));
    }

    public BlocNotesDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        Iterator<Table> tables = sTables.iterator();
        while (tables.hasNext()) {
           db.execSQL(tables.next().getCreateStatement());
        }

        ContentValues values = new ContentValues();
        values.put("name", "Uncategorized");
        long uncategorizedRowId = db.insert("Notebook", null, values);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Iterator<Table> tables = sTables.iterator();
        while (tables.hasNext()) {
            tables.next().onUpgrade(db, oldVersion, newVersion);
        }
    }
}
