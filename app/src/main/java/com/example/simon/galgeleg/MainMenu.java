package com.example.simon.galgeleg;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class MainMenu extends Fragment implements View.OnClickListener {

    private Button helpBut, settingsBut, gameBut;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("MainMenu", "the fragment was shown!!");

        View source = inflater.inflate(R.layout.activity_mainmenu, container, false);

        gameBut = (Button) source.findViewById(R.id.button);
        gameBut.setText("Play Hangman");

        settingsBut = (Button) source.findViewById(R.id.button2);
        settingsBut.setText("Settings");

        helpBut = (Button) source.findViewById(R.id.button3);
        helpBut.setText("Help");

        gameBut.setOnClickListener(this);
        settingsBut.setOnClickListener(this);
        helpBut.setOnClickListener(this);

        return source;

    }

    public void onClick(View v) {
        if (v == gameBut) {

          StartGame fragment = new StartGame();
          Bundle args = new Bundle();
          args.putString("state","");
          fragment.setArguments(args);

          getFragmentManager().beginTransaction()
                  .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                  .replace(R.id.fragments, fragment)
                  .addToBackStack(null)
                  .commit();
        } else if (v == settingsBut) {

            Settings fragment = new Settings();
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