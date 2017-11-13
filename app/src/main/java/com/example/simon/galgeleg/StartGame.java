package com.example.simon.galgeleg;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Simon on 13-11-2017.
 */

public class StartGame extends Fragment implements View.OnClickListener {

    Button addBut, randBut, listBut;
    TextView starttv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("StartGame", "the fragment was shown!!");

        View source = inflater.inflate(R.layout.activity_start, container, false);

        addBut = (Button) source.findViewById(R.id.addBut);
        randBut = (Button) source.findViewById(R.id.randBut);
        listBut = (Button) source.findViewById(R.id.listBut);
        starttv = (TextView) source.findViewById(R.id.starttv);

        addBut.setText("Add word to play with");
        randBut.setText("Play with a random word");
        listBut.setText("Choose a word from a list");
        starttv.setText("What do you want to do?");

        addBut.setOnClickListener(this);
        randBut.setOnClickListener(this);
        listBut.setOnClickListener(this);


        return source;
    }

    @Override
    public void onClick(View v) {

        if (v == addBut) {

            Bundle args = new Bundle();
            args.putString("state", "game");
            WriteWord fragment = new WriteWord();
            fragment.setArguments(args);

            getFragmentManager().beginTransaction()
                    .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                    .replace(R.id.fragments, fragment)
                    .addToBackStack(null)
                    .commit();

        } else if (v == randBut) {

            Bundle args = new Bundle();
            args.putString("word", "noWord");
            Game fragment = new Game();
            fragment.setArguments(args);

            getFragmentManager().beginTransaction()
                    .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                    .replace(R.id.fragments, fragment)
                    .addToBackStack(null)
                    .commit();

        } else if (v == listBut) {

            Bundle args = new Bundle();
            args.putString("state", "list");
            WordList fragment = new WordList();
            fragment.setArguments(args);

            getFragmentManager().beginTransaction()
                    .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                    .replace(R.id.fragments, fragment)
                    .addToBackStack(null)
                    .commit();

        }
    }
}

