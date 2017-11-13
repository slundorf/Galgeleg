package com.example.simon.galgeleg;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.Window;

import com.example.simon.galgeleg.Logic.Galgelogik;

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
        // Function that loads the data stored in the SharedPreferences
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);

        String amountw = preferences.getString("amountw", ""); // Gets the amount of words saved
        String amounths = preferences.getString("amounths", ""); // Gets the amount of high scores saved

        if (!Objects.equals(amountw, "")) { // Only triggers if there are saved words
            int count = Integer.valueOf(amountw); // Parses the string amount to an int

            for (int i = 0; i <= count; i++) { // For loop that uses the amount of words saved to run through all the keys the words are saved as, adding them to the list in the logic

                String word = preferences.getString("word" + String.valueOf(i), "");
                logic.tilføjOrd(word);

            }
        }
        if (!Objects.equals(amounths, "")) { // Only triggers if there are saved high scores.
            int count = Integer.valueOf(amounths); // Parses the string amount to an int

            for (int i = 0; i <= count; i++) { // For loop that uses the amount of high scores saved to run through all the keys the high scores are saved as, adding them to the list in the logic

                String word = preferences.getString("word" + String.valueOf(i), "");
                String wrong = preferences.getString("wrong" + String.valueOf(i), "");
                String player = preferences.getString("player" + String.valueOf(i), "");
                logic.setHighscorelist(word, wrong, player);

            }
        }

    }
}