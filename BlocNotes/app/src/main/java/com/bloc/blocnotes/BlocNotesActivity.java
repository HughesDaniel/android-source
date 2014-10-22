package com.bloc.blocnotes;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;


public class BlocNotesActivity extends Activity implements
        CustomStyleDialogFragment.CustomStyleInterface,
        NavigationDrawerFragment.NavigationDrawerCallbacks,
        AddNotebookDialogFragment.AddNotebookDialogListener {

    private static final String TAG = ".BlocNotesActivity";

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    // Fragment that displays our note editor
    private NoteFragment noteFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bloc_notes);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));

        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();

        noteFragment = new NoteFragment();
        fragmentTransaction.replace(R.id.container, noteFragment).commit();
    }

    // required method for NavigationDrawerCallBack Interface
    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, NotesDisplayFragment.newInstance(position))
                .commit();
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_section1);
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                break;
            case 4:
                mTitle = "Extra 1";
                break;
            case 5:
                mTitle = "Extra 2";
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.bloc_notes, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_erase) {
            return true;
        }
        if (id == R.id.action_customize) {
            // show dialog
            FragmentManager fm = getFragmentManager();
            CustomStyleDialogFragment customDialog = new CustomStyleDialogFragment();
            // adds activity to fragments list of observers to be notified of changes
            customDialog.addListener(this);
            customDialog.show(fm, "fragment_custom_style_dialog");
            return true;
        }
        if (id == R.id.action_settings) {
            getFragmentManager().beginTransaction()
                    .replace(R.id.container, new PrefsFragment())
                    .commit();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // required method for CustomStyleInterface
    @Override
    public void onStyleChange(CustomStyleDialogFragment dialog, int styleid) {
        noteFragment.setStyle(styleid);
    }

    // required method for CustomStyleInterface
    @Override
    public void onFontChange(CustomStyleDialogFragment dialog, String fontName) {
        noteFragment.setFont(fontName);
    }

    // required method for CustomStyleInterface
    @Override
    public void onThemeChange(CustomStyleDialogFragment dialog, int themeId) {

    }

    // required method for the AddNoteBookActionListener interface
    // adds a new row to the notebook table in our database
    @Override
    public void onFinishedAddNotebook(final String name) {

        // adds the notebook to the database
        new NotebookModel(name);
        // tells the fragment to update the view so the new notebook is displayed
        mNavigationDrawerFragment.updateNotebookAdapter(name);
    }
}
