package com.example.simon.galgeleg;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Objects;

import static com.example.simon.galgeleg.Main_Activity.logic;

/**
 * Created by Simon on 06-11-2017.
 */

public class EndGame extends Fragment implements View.OnClickListener {

    private Button endBut, endBut2, endBut3;
    private TextView endtv, endtv2;
    private EditText endet;
    private ImageView endiv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("EndGame", "the fragment was shown!!");

        View source = inflater.inflate(R.layout.activity_end, container, false);

        endBut = (Button) source.findViewById(R.id.endBut);
        endBut2 = (Button) source.findViewById(R.id.endBut2);
        endBut3 = (Button) source.findViewById(R.id.endBut3);
        endet = (EditText) source.findViewById(R.id.endet);
        endtv = (TextView) source.findViewById(R.id.endtv);
        endtv2 = (TextView) source.findViewById(R.id.endtv2);
        endiv = (ImageView) source.findViewById(R.id.endiv);

        endet.setHint("Enter your name here");
        endiv.setImageResource(Game.imageIDs[logic.getAntalForkerteBogstaver()]);

        String condition = getArguments().getString("condition", String.valueOf(0));

        if (Objects.equals(condition, "win")) {

            endtv.setText("You won the game! You used " + logic.getGuesses() + " guesses to guess the word: " + logic.getOrdet() + "!");

            checkHighscore();

        } else if (Objects.equals(condition, "loss")) {

            endtv.setText("You lost the game! You couldn't guess the word: " + logic.getOrdet() + " in time!");

            endGame();

        }

        endBut.setOnClickListener(this);
        endBut2.setOnClickListener(this);
        endBut3.setOnClickListener(this);

        return source;

    }

    @Override
    public void onClick(View v) {

        if (v == endBut) {

            FragmentManager fm = getFragmentManager();
            fm.popBackStackImmediate();
            fm.popBackStackImmediate();
            fm.popBackStackImmediate();

        } else if (v == endBut2) {

            FragmentManager fm = getFragmentManager();
            fm.popBackStackImmediate();
            fm.popBackStackImmediate();
            fm.popBackStackImmediate();
            fm.popBackStackImmediate();

        } else if (v == endBut3) {

            logic.addHighscore(endet.getText().toString());
            logic.saveHighScore(getContext());
            endGame();

        }

    }

    public void checkHighscore() {

        if (logic.getHighscoreList().size() == 0) {

            newHighscore();

        } else {

            for (int i = 0; i < logic.getHighscoreList().size(); i++) {

                if (logic.getHighscoreList().get(i).get("word").equals(logic.getOrdet())) {

                    System.out.println("TEST");
                    System.out.println(logic.getGuesses());
                    System.out.println(logic.getHighscoreList().get(i).get("wrong"));

                    if (logic.getGuesses() < Integer.valueOf(logic.getHighscoreList().get(i).get("wrong"))) {

                        System.out.println("FUCK");
                        newHighscore();

                    } else {

                        endBut3.setVisibility(View.GONE);
                        endet.setVisibility(View.GONE);

                    }

                } else {

                    endGame();

                }
            }
        }
    }

    public void newHighscore() {

        endtv2.setText("This is a new record for that word! Write your name to get on the list of highscores");
        endBut.setVisibility(View.GONE);
        endBut2.setVisibility(View.GONE);
        endBut3.setVisibility(View.VISIBLE);
        endet.setVisibility(View.VISIBLE);

    }

    public void endGame() {
        endet.setVisibility(View.GONE);
        endBut3.setVisibility(View.GONE);
        endBut.setVisibility(View.VISIBLE);
        endBut2.setVisibility(View.VISIBLE);
        endtv2.setText("Want to play again?");
    }

}
