package com.bloc.blocnotes;

import android.app.Application;
import android.content.Context;

import com.bloc.blocnotes.com.bloc.database.BlocNotesDbHelper;

/**
 * Created by Daniel on 10/15/2014.
 */
public class BlocNotesApplication extends Application {

    BlocNotesDbHelper BlocDb;

    public BlocNotesApplication() {
    }

    @Override
    public void onCreate() {
        BlocDb = new BlocNotesDbHelper(this);
    }

    public BlocNotesDbHelper getBlocDb() {
        return BlocDb;
    }

    public static BlocNotesApplication get(Context context) {
        return (BlocNotesApplication) context.getApplicationContext();
    }
}
