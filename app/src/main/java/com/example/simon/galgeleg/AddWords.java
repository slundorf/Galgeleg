package com.example.simon.galgeleg;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.simon.galgeleg.Logic.Galgelogik;

/**
 * Created by Simon on 22-10-2017.
 */

public class AddWords extends Fragment implements View.OnClickListener {

    private EditText wordet;
    private Button wordBut3;
    private Button wordBut4;
    private Button wordBut5;
    private TextView wordtv;
    private Galgelogik logic;

    public AddWords(Galgelogik l) {

        logic = l;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

    View source = inflater.inflate(R.layout.activity_words, container, false);

        wordet = (EditText) source.findViewById(R.id.wordet);

        wordBut3 = (Button) source.findViewById(R.id.wordBut3);

        wordBut4 = (Button) source.findViewById(R.id.wordBut4);

        wordBut5 = (Button) source.findViewById(R.id.wordBut5);

        wordtv = (TextView) source.findViewById(R.id.wordtv);

        wordet.setHint("Enter the word you wish to add");

        wordBut3.setOnClickListener(this);
        wordBut4.setOnClickListener(this);
        wordBut5.setOnClickListener(this);

        return source;

    }

    @Override
    public void onClick(View v) {

        if (v == wordBut3) {

            String word = wordet.getText().toString();

            logic.tilføjOrd(word);

            updateScreen();

        } else if (v == wordBut4) {

            wordBut3.setVisibility(View.VISIBLE);
            wordet.setVisibility(View.VISIBLE);
            wordtv.setVisibility(View.GONE);
            wordBut4.setVisibility(View.GONE);
            wordBut5.setVisibility(View.GONE);

        } else if (v == wordBut5) {

            getFragmentManager().beginTransaction()
                    .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                    .replace(R.id.fragments, new AddWords(logic))
                    .addToBackStack(null)
                    .commit();

        }

    }

    public void updateScreen() {

        wordBut3.setVisibility(View.GONE);
        wordet.setVisibility(View.GONE);
        wordBut4.setVisibility(View.VISIBLE);
        wordBut5.setVisibility(View.VISIBLE);
        wordtv.setVisibility(View.VISIBLE);

    }


}
