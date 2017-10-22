package com.example.simon.galgeleg;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.support.v4.app.Fragment;
import android.widget.TextView;

import com.example.simon.galgeleg.Logic.Galgelogik;

public class MainMenu extends Fragment implements View.OnClickListener {

    Button helpBut, settingsBut, gameBut;
    TextView tv1;
    Galgelogik logic = new Galgelogik();

    @Override
    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle savedInstanceState) {
        View source = i.inflate(R.layout.activity_mainmenu, container, false);


        gameBut = (Button) source.findViewById(R.id.button);
        gameBut.setText("Play Hangman");

        settingsBut = (Button) source.findViewById(R.id.button2);
        settingsBut.setText("Settings");

        helpBut = (Button) source.findViewById(R.id.button3);
        helpBut.setText("Help");
      //  tv1 = (TextView) source.findViewById(R.id.tv1);
      //  tv1.setText("Main Menu");

        gameBut.setOnClickListener(this);
        settingsBut.setOnClickListener(this);
        helpBut.setOnClickListener(this);

        return source;
    }

    public void onClick(View v) {
        if (v == gameBut) {

            Game fragment = new Game(logic);
            Bundle arguments = new Bundle();
            fragment.setArguments(arguments);

            getFragmentManager().beginTransaction()
                    .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                    .replace(R.id.fragments, fragment)
                    .addToBackStack(null)
                    .commit();
        } else if (v == settingsBut) {

            Settings fragment = new Settings(logic);
            Bundle arguments = new Bundle();
            fragment.setArguments(arguments);

            getFragmentManager().beginTransaction().setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                    .replace(R.id.fragments, fragment)
                    .addToBackStack(null)
                    .commit();


        } else if (v == helpBut) {

        getFragmentManager().beginTransaction()
                .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                .replace(R.id.fragments, new Help())
                .addToBackStack(null)
                .commit();

    }

    }
}