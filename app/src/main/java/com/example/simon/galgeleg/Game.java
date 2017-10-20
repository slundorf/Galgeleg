package com.example.simon.galgeleg;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TextView;
import android.support.v4.app.Fragment;

import com.example.simon.galgeleg.Logic.Galgelogik;

/**
 * Created by Simon on 20-10-2017.
 */

public class Game extends Fragment implements View.OnClickListener {

    private TextView info;
    private Button gameBut;
    private EditText et;
    private Galgelogik logic;
    private TextView tv1;

    public Game(Galgelogik l) {
    logic = l;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("Game", "the fragment was shown!!");

        TableLayout tl = new TableLayout(getActivity());

 //       tv1 = (TextView) tv1.findViewById(R.id.tv1);
//        tv1.setText("");

        info = new TextView(getActivity());
        info.setText("Welcome to hangman!." +
                "\nThis is the word you have to guess: "+logic.getSynligtOrd() +
                "\nWrite the letter you wish to guess on underneath and hit 'Play'.\n");

        tl.addView(info);

        et = new EditText(getActivity());
        et.setHint("Write your letter here.");
        tl.addView(et);

        gameBut = new Button(getActivity());
        gameBut.setText("Play");
        gameBut.setCompoundDrawablesWithIntrinsicBounds(android.R.drawable.ic_media_play, 0, 0, 0);
        tl.addView(gameBut);

        gameBut.setOnClickListener(this);

        return tl;
    }

    @Override
    public void onClick(View view) {
        String letter = et.getText().toString();
        if (letter.length() != 1) {
            et.setError("Only write ONE letter");
            return;
        }
        logic.gætBogstav(letter);
        et.setText("");
        et.setError(null);
        opdaterSkærm();
    }


    private void opdaterSkærm() {

        info.setText("Guess the word: " + logic.getSynligtOrd());
        info.append("\n\nYou have " + logic.getAntalForkerteBogstaver() + " wrong:" + logic.getBrugteBogstaver());

        if (logic.erSpilletVundet()) {
            info.append("\nYou win!");
        }
        if (logic.erSpilletTabt()) {
            info.setText("You have lost. The word was : " + logic.getOrdet());
        }
    }
}