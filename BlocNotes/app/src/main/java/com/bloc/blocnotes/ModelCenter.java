package com.bloc.blocnotes;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Daniel on 10/21/2014.
 */
public abstract class ModelCenter<T extends Model> {

    // Will store the name of the table in the DB
    protected String mTableName;

    // Hashmap that will store the model objects
    protected Map<Long, T> mModels;

    public ModelCenter(String table) {
        mTableName = table;
        mModels = new HashMap<Long, T>();
    }

    // returns a model object given its unique rowId
    public Model get(long rowId) {
        T model = null;
        if (mModels.containsKey(rowId)) {
            model = mModels.get(rowId);
        } else {
            model = _createModel();
        }

        return model;
    }

    // Used to create an object if it doesnt already exist
    protected abstract  T _createModel();
}
