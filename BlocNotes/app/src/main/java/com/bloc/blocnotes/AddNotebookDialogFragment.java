package com.bloc.blocnotes;

import android.app.DialogFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Daniel on 10/16/2014.
 */
public class AddNotebookDialogFragment extends DialogFragment {

    public interface AddNotebookDialogListener {
        void onFinishedAddNotebook(String name);
    }

    private static final String TAG = ".AddNoteBookDialogFragment";

    EditText mEditText;

    public AddNotebookDialogFragment() {
        // required constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle bundle) {
        Log.d(TAG, "onCreateView entered");
        View view = inflater.inflate(R.layout.fragment_simple_edit_text, container, false);
        getDialog().setTitle("Add Notebook");

        mEditText = (EditText) view.findViewById(R.id.et_note);
        mEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                // 0 right now for debug as emulator doesnt have soft keyboard
                // replace with EditorInfo.IME_ACTION_DONE
                Log.d(TAG, "actionId = " + actionId);
                if (actionId == 0) {
                    // Return string to host and close dialog
                    AddNotebookDialogListener activity = (AddNotebookDialogListener) getActivity();
                    activity.onFinishedAddNotebook(mEditText.getText().toString());
                    closeDialog();

                    return true;
                }
                return false;
            }
        });

        return view;
    }

    private void closeDialog() {
        this.dismiss();
    }
}
