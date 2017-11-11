package com.example.simon.galgeleg;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import static com.example.simon.galgeleg.Main_Activity.logic;

/**
 * Created by Simon on 20-10-2017.
 */

public class Game extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener {

    private Button guessBut, addBut, randBut, listBut;
    private EditText et;
    private ImageView gameiv;
    private TextView gametv;
    private ListView glv;
    private ArrayList<String> possibleWords;
    static Integer[] imageIDs = {
            R.drawable.galge,
            R.drawable.forkert1,
            R.drawable.forkert2,
            R.drawable.forkert3,
            R.drawable.forkert4,
            R.drawable.forkert5,
            R.drawable.forkert6,
    };
    private int counter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("Game", "the fragment was shown!!");

        View source = inflater.inflate(R.layout.activity_game, container, false);

        randBut = (Button) source.findViewById(R.id.randBut);
        addBut = (Button) source.findViewById(R.id.addBut);
        listBut = (Button) source.findViewById(R.id.listBut);
        guessBut = (Button) source.findViewById(R.id.guessBut);
        gametv = (TextView) source.findViewById(R.id.gametv);
        gameiv = (ImageView) source.findViewById(R.id.gameiv);
        et = (EditText) source.findViewById(R.id.et);

        glv = (ListView) source.findViewById(R.id.gameList);

        possibleWords = logic.getMuligeOrd();

        String[] wordArray = possibleWords.toArray(new String[possibleWords.size()]);

        ArrayAdapter adapter = new ArrayAdapter<String>(getActivity(), R.layout.activity_textview, R.id.wordtv,wordArray);

        glv.setAdapter(adapter);

        guessBut.setOnClickListener(this);
        randBut.setOnClickListener(this);
        addBut.setOnClickListener(this);
        listBut.setOnClickListener(this);
        glv.setOnItemClickListener(this);

        chooseWord();

        return source;
    }

    @Override
    public void onClick(View v) {

        if (v == guessBut) {

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
        } else if (v == listBut) {

            listBut.setVisibility(View.GONE);
            addBut.setVisibility(View.GONE);
            randBut.setVisibility(View.GONE);
            guessBut.setVisibility(View.GONE);
            gameiv.setVisibility(View.GONE);
            et.setVisibility(View.GONE);
            glv.setVisibility(View.VISIBLE);
            gametv.setText("Choose a word you wish you play with:");


        } else if (v == addBut) {

            String word = et.getText().toString();

            if (word.length() == 0 && addBut.getText().equals("Play")) {
                et.setError("Enter a word to continue!");
            } else if (word.length() != 0) {
                startGame(et.getText().toString());
            } else if (word.length() == 0) {
                addBut.setText("Play");
                listBut.setVisibility(View.GONE);
                randBut.setVisibility(View.GONE);
                gametv.setVisibility(View.GONE);
                et.setVisibility(View.VISIBLE);
                et.setHint("Enter your word.");
            }

        } else if (v == randBut) {

            startGame(null);

        }

    }


    private void updateScreen() {

        gametv.setText("Guess the word: " + logic.getSynligtOrd());
        if (logic.erSidsteBogstavKorrekt() == true) {

            gametv.append("\n\nYou have " + logic.getAntalForkerteBogstaver() + " wrong:" + logic.getBrugteBogstaver());

        } else if (logic.erSidsteBogstavKorrekt() == false) {

            gametv.append("\n\nYou have " + logic.getAntalForkerteBogstaver() + " wrong:" + logic.getBrugteBogstaver());

            gameiv.setImageResource(imageIDs[logic.getAntalForkerteBogstaver()]);

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

    public void chooseWord() {

        gametv.setText("Choose to play with a randomly chosen word, pick one from a list or write your own");
        listBut.setText("Choose word from list");
        addBut.setText("Write a word to play with");
        randBut.setText("Play with random word");

    }

    public void startGame(String w) {
        logic.nulstil();

        listBut.setVisibility(View.GONE);
        addBut.setVisibility(View.GONE);
        randBut.setVisibility(View.GONE);
        glv.setVisibility(View.GONE);
        gametv.setVisibility(View.VISIBLE);
        guessBut.setVisibility(View.VISIBLE);
        gameiv.setVisibility(View.VISIBLE);
        et.setVisibility(View.VISIBLE);
        et.setText("");
        et.setHint("Write your letter here.");

        if (w != null) {

            et.setText("");
            logic.setOrdet(w);
            logic.opdaterSynligtOrd();

        }

            gametv.setText("This is the word you have to guess: \n" + logic.getSynligtOrd() +
                    "\nWrite the letter or word you wish to guess on underneath and hit 'Guess'.\n");

        gameiv.setImageResource(imageIDs[logic.getAntalForkerteBogstaver()]);

    }

    private void endGame() {

        et.setText("");

        EndGame fragment = new EndGame();
        Bundle arguments = new Bundle();
        fragment.setArguments(arguments);

        getFragmentManager().beginTransaction().setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                .replace(R.id.fragments, fragment)
                .addToBackStack(null)
                .commit();

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        String word = possibleWords.get(position);
        startGame(word);

    }
}