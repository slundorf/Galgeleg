package com.example.simon.galgeleg;

import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.simon.galgeleg.Logic.Galgelogik;

/**
 * Created by Simon on 20-10-2017.
 */

public class Game extends Fragment implements View.OnClickListener {

    private TextView info;
    private Button gameBut;
    private Button yesBut;
    private Button noBut;
    private EditText et;
    private Galgelogik logic;
    private ImageView gameiv;
    private TextView gametv;
    private int counter;
    private Image img;
    private Integer[] imageIDs = {
            R.drawable.forkert1,
            R.drawable.forkert2,
            R.drawable.forkert3,
            R.drawable.forkert4,
            R.drawable.forkert5,
            R.drawable.forkert6,
    };

    public Game(Galgelogik l) {
    logic = l;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("Game", "the fragment was shown!!");

        View source = inflater.inflate(R.layout.activity_game, container, false);

        gameBut = (Button) source.findViewById(R.id.button4);
        yesBut = (Button) source.findViewById(R.id.button5);
        noBut = (Button) source.findViewById(R.id.button6);
        gametv = (TextView) source.findViewById(R.id.gametv);
        gameiv = (ImageView) source.findViewById(R.id.gameiv);
        et = (EditText) source.findViewById(R.id.et);

        startGame();

        gameBut.setOnClickListener(this);
        yesBut.setOnClickListener(this);
        noBut.setOnClickListener(this);

        return source;
    }

    @Override
    public void onClick(View v) {

        if (v == gameBut) {

            String letter = et.getText().toString();
            if (letter.length() != 1 && letter.length() != logic.getOrdet().length()) {
                et.setError("Only write ONE letter or guess the WHOLE word");
                return;
            }
            if (letter.length() == logic.getOrdet().length()) {
                logic.gætOrd(letter);
                updateScreen();
            } else {
                logic.gætBogstav(letter);
                et.setText("");
                et.setError(null);
                updateScreen();
            }
        }

        if (v == yesBut) {
            startGame();
        }

        if (v == noBut) {
            Fragment fragment = new MainMenu();
            getFragmentManager().beginTransaction()
                    .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                    .replace(R.id.fragments, fragment)
                    .commit();
        }

    }


    private void updateScreen() {

        gametv.setText("Guess the word: " + logic.getSynligtOrd());
        if (logic.erSidsteBogstavKorrekt() == true) {
            gametv.append("\n\nYou have " + logic.getAntalForkerteBogstaver() + " wrong:" + logic.getBrugteBogstaver());
        } else if (logic.erSidsteBogstavKorrekt() == false) {
            gametv.append("\n\nYou have " + logic.getAntalForkerteBogstaver() + " wrong:" + logic.getBrugteBogstaver());
            gameiv.setImageResource(imageIDs[counter]);
            counter++;
        }

        if (logic.erSpilletVundet()) {
            gametv.append("\nYou win!");
            endGame();
        }
        if (logic.erSpilletTabt()) {
            gametv.setText("You have lost. The word was : " + logic.getOrdet());
            endGame();
        }
    }

    private void startGame() {
        logic.nulstil();
        counter = 0;

        gametv.setText("Welcome to hangman!." +
                "\nThis is the word you have to guess: "+logic.getSynligtOrd() +
                "\nWrite the letter you wish to guess on underneath and hit 'Play'.\n");


        et.setHint("Write your letter here.");

        gameiv.setImageResource(R.drawable.galge);

        gameBut.setVisibility(View.VISIBLE);
        et.setVisibility(View.VISIBLE);
        yesBut.setVisibility(View.GONE);
        noBut.setVisibility(View.GONE);
    }

    private void endGame() {
        gameBut.setVisibility(View.GONE);
        et.setVisibility(View.GONE);
        yesBut.setVisibility(View.VISIBLE);
        noBut.setVisibility(View.VISIBLE);
        gametv.append("\nWant to play again?");
    }
}