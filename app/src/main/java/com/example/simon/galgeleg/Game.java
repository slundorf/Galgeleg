package com.example.simon.galgeleg;

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

import java.util.Objects;

import static com.example.simon.galgeleg.Main_Activity.logic;

/**
 * Created by Simon on 20-10-2017.
 */

public class Game extends Fragment implements View.OnClickListener {

    private Button guessBut;
    private EditText et;
    private ImageView gameiv;
    private TextView gametv;
    static Integer[] imageIDs = {
            R.drawable.galge,
            R.drawable.forkert1,
            R.drawable.forkert2,
            R.drawable.forkert3,
            R.drawable.forkert4,
            R.drawable.forkert5,
            R.drawable.forkert6,
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("Game", "the fragment was shown!!");

        View source = inflater.inflate(R.layout.activity_game, container, false);

        guessBut = (Button) source.findViewById(R.id.guessBut);
        gametv = (TextView) source.findViewById(R.id.gametv);
        gameiv = (ImageView) source.findViewById(R.id.gameiv);
        et = (EditText) source.findViewById(R.id.et);

        guessBut.setOnClickListener(this);

        String word = getArguments().getString("word", String.valueOf(0));

        System.out.println(word);

        startGame(word);

        return source;
    }

    @Override
    public void onClick(View v) {

        if (v == guessBut) {

            String letter = et.getText().toString().toLowerCase();
            if (letter.length() != 1 && letter.length() != logic.getOrdet().length()) {
                et.setError("Only write ONE letter or guess the WHOLE word");
                return;
            }
            if (letter.length() == logic.getOrdet().length()) {
                logic.gætOrd(letter);
                updateScreen();
                et.setText("");
            } else {
                logic.gætBogstav(letter);
                et.setText("");
                et.setError(null);
                updateScreen();
            }

        }
    }

    private void updateScreen() {

        gametv.setText("Guess the word: " + logic.getSynligtOrd());
        if (logic.erSidsteBogstavKorrekt()) {

            gametv.append("\n\nYou have " + logic.getAntalForkerteBogstaver() + " wrong:" + logic.getBrugteBogstaver());

        } else if (!logic.erSidsteBogstavKorrekt()) {

            gametv.append("\n\nYou have " + logic.getAntalForkerteBogstaver() + " wrong:" + logic.getBrugteBogstaver());

            gameiv.setImageResource(imageIDs[logic.getAntalForkerteBogstaver()]);

        }

        if (logic.erSpilletVundet()) {

            endGame("win");

        }
        if (logic.erSpilletTabt()) {

            endGame("loss");
        }
    }

    public void startGame(String w) {
        logic.nulstil();

        et.setText("");
        et.setHint("Write your letter here.");

        if (!Objects.equals(w, "noWord")) {

            et.setText("");
            logic.setOrdet(w);
            logic.opdaterSynligtOrd();

        }

            gametv.setText("This is the word you have to guess: \n" + logic.getSynligtOrd() +
                    "\nWrite the letter or word you wish to guess on underneath and hit 'Guess'.\n");

        gameiv.setImageResource(imageIDs[logic.getAntalForkerteBogstaver()]);

    }

    private void endGame(String condition) {

        et.setText("");

        EndGame fragment = new EndGame();
        Bundle args = new Bundle();
        args.putString("condition", condition);
        fragment.setArguments(args);

        getFragmentManager().beginTransaction().setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                .replace(R.id.fragments, fragment)
                .addToBackStack(null)
                .commit();

    }

}