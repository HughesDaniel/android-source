package com.bloc.blocnotes;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Daniel on 10/23/2014.
 */
public class NotesAdapter extends BaseAdapter {

    private static final String TAG = ".NotesAdapter.java";

    private List<String> mNotesList;

    private Context mContext;

    public NotesAdapter(Context context, List<String> noteList) {
        Log.d(TAG, "NotesAdapter being constructed");
        mNotesList = noteList;
        mContext = context;
    }

    @Override
    public int getCount() {
        return mNotesList.size();
    }

    @Override
    public Object getItem(int position) {
        return mNotesList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View cView = convertView;

        if (cView == null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            cView = inflater.inflate(R.layout.notebook_note, parent, false);
        }

        String note = (String) getItem(position);

        TextView noteText = (TextView) cView.findViewById(R.id.tv_notebook_note);
        noteText.setText(note);

        final ImageButton threeDots = (ImageButton) cView.findViewById(R.id.ib_note_popup);
        threeDots.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(mContext, threeDots);
                popup.getMenuInflater().inflate(R.menu.popup_menu_noteslist, popup.getMenu());


                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch(item.getItemId()) {
                            case (R.id.popup_item_1):
                                Toast.makeText(mContext, "ONE!", Toast.LENGTH_LONG).show();
                                return true;
                            case (R.id.popup_item_2):
                                Toast.makeText(mContext, "TWO!", Toast.LENGTH_LONG).show();
                                return true;
                        }

                        return false;
                    }
                });

                popup.show();
            }
        });

        return cView;
    }
}
