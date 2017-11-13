package com.example.simon.galgeleg;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Simon on 20-10-2017.
 */

public class Settings extends Fragment implements View.OnClickListener {
    private Button wordBut, wordBut2, scoreBut;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("Settings", "the fragment was shown!");

        View source = inflater.inflate(R.layout.activity_settings, container, false);

        wordBut = (Button) source.findViewById(R.id.wordBut);
        wordBut2 = (Button) source.findViewById(R.id.wordBut2);
        scoreBut = (Button) source.findViewById(R.id.scoreBut);

        wordBut.setOnClickListener(this);
        wordBut2.setOnClickListener(this);
        scoreBut.setOnClickListener(this);

        return source;

    }

    public void onClick(View v) {

        if (v == wordBut) {

            Bundle args = new Bundle();
            args.putString("state", "noList");
            WordList fragment = new WordList();
            fragment.setArguments(args);

            getFragmentManager().beginTransaction()
                    .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                    .replace(R.id.fragments, fragment)
                    .addToBackStack(null)
                    .commit();

        } else if (v == wordBut2) {

            getFragmentManager().beginTransaction()
                    .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                    .replace(R.id.fragments, new AddWords())
                    .addToBackStack(null)
                    .commit();

        } else if (v == scoreBut) {

            getFragmentManager().beginTransaction()
                    .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                    .replace(R.id.fragments, new HighScore())
                    .addToBackStack(null)
                    .commit();

        }
    }
}