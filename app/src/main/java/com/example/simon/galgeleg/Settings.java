package com.example.simon.galgeleg;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TextView;

import com.example.simon.galgeleg.Logic.Galgelogik;

/**
 * Created by Simon on 20-10-2017.
 */

public class Settings extends Fragment implements View.OnClickListener {
    private TextView info;
    private Button wordBut;
    private Button wordBut2;
    private EditText et;
    private Galgelogik logic;

    public Settings(Galgelogik l) {
        logic = l;
    }

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
                    .replace(R.id.fragments, new WordList(logic))
                    .addToBackStack(null)
                    .commit();

        } else if (v == wordBut2) {

            getFragmentManager().beginTransaction()
                    .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                    .replace(R.id.fragments, new AddWords(logic))
                    .addToBackStack(null)
                    .commit();

        }
    }
}