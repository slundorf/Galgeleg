package com.example.simon.galgeleg;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Simon on 20-10-2017.
 */

public class Settings extends Fragment implements View.OnClickListener {
    private Button wordBut;
    private Button wordBut2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("Settings", "the fragment was shown!");

        View source = inflater.inflate(R.layout.activity_settings, container, false);

        wordBut = (Button) source.findViewById(R.id.wordBut);
        wordBut2 = (Button) source.findViewById(R.id.wordBut2);

        wordBut.setOnClickListener(this);
        wordBut2.setOnClickListener(this);

        return source;

    }

    public void onClick(View v) {

        if (v == wordBut) {

            getFragmentManager().beginTransaction()
                    .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                    .replace(R.id.fragments, new WordList())
                    .addToBackStack(null)
                    .commit();

        } else if (v == wordBut2) {

            getFragmentManager().beginTransaction()
                    .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                    .replace(R.id.fragments, new AddWords())
                    .addToBackStack(null)
                    .commit();

        }
    }
}