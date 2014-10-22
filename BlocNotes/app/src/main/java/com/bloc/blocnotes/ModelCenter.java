package com.bloc.blocnotes;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Daniel on 10/21/2014.
 */
public abstract class ModelCenter<T extends Model> {

    protected String mTableName;
    protected Map<Long, T> mModels;

    public ModelCenter(String table) {
        mTableName = table;
        mModels = new HashMap<Long, T>();
    }

    public Model get(long rowId) {
        T model = null;
        if (mModels.containsKey(rowId)) {
            model = mModels.get(rowId);
        } else {
            model = _createModel();
        }

        return model;
    }


    protected abstract  T _createModel();
}
