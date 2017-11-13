package com.example.simon.galgeleg;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.Window;
import java.util.HashMap;

import com.example.simon.galgeleg.Logic.Galgelogik;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by Simon on 20-10-2017.
 */

public class Main_Activity extends AppCompatActivity {

    static Galgelogik logic = new Galgelogik();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_ACTION_BAR);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frame_layout);

        loadData();

        if (savedInstanceState == null) {
            Fragment fragment = new Welcome();

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragments, fragment)
                    .commit();
        }

        setTitle("Hangman");
     //   Drawable draw = getResources().getDrawable(R.drawable.hangmanlogo);
    //    getSupportActionBar().setBackgroundDrawable(draw);
          getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    public void loadData() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String amount = preferences.getString("amount", "");

        if (!Objects.equals(amount, "")) {
            int count = Integer.valueOf(amount);

            for (int i = 0; i <= count; i++) {

                String word = preferences.getString(String.valueOf(i), "");
                logic.tilføjOrd(word);

            }
        }

    }
}