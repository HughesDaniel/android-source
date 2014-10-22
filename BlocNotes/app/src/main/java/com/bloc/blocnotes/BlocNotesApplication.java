package com.bloc.blocnotes;

import android.app.Application;

import com.bloc.blocnotes.com.bloc.database.BlocNotesDbHelper;

/**
 * Created by Daniel on 10/15/2014.
 */
public class BlocNotesApplication extends Application {

    private static BlocNotesDbHelper BlocDb;

    public BlocNotesApplication() {
    }

    @Override
    public void onCreate() {
        BlocDb = new BlocNotesDbHelper(this);

    }

    public static BlocNotesDbHelper getBlocDb() {
        return BlocDb;
    }

    public static BlocNotesApplication get() {
        //return (BlocNotesApplication) context.getApplicationContext();
        return null;


    }
}
