package com.bloc.blocnotes;

import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daniel on 10/21/2014.
 */
public class NotebookCenter extends ModelCenter<NotebookModel> {


    public NotebookCenter() {
        super("Notebook");
    }

    @Override
    protected NotebookModel _createModel() {
        return null;
    }

    public List<String> getNotebookNames() {
        List<String> notebookNames = new ArrayList<String>();

        Cursor names = BlocNotesApplication.getBlocDb().getReadableDatabase()
                .query(true, mTableName, new String[] {"name"},
        null, null, null, null, null, null);

        int columnIndex = names.getColumnIndex("name");
;
        while(names.moveToNext()) {
            notebookNames.add(names.getString(columnIndex));
        }

        names.close();

        return notebookNames;
    }
}
