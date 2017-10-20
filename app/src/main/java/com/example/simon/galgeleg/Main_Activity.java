package com.example.simon.galgeleg;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.Window;

/**
 * Created by Simon on 20-10-2017.
 */

public class Main_Activity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_ACTION_BAR);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainmenu);

        if (savedInstanceState == null) {
            Fragment fragment = new Welcome();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragments, fragment)
                    .commit();
        }

        setTitle("Hangman");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}