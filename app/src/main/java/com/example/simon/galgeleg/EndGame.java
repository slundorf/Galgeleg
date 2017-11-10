package com.example.simon.galgeleg;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import static com.example.simon.galgeleg.MainMenu.logic;

/**
 * Created by Simon on 06-11-2017.
 */

public class EndGame extends Fragment implements View.OnClickListener {

    private Button endBut, endBut2;
    private TextView endtv, endtv2;
    private ImageView endiv;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("EndGame", "the fragment was shown!!");

        View source = inflater.inflate(R.layout.activity_end, container, false);

        endBut = (Button) source.findViewById(R.id.endBut);
        endBut2 = (Button) source.findViewById(R.id.endBut2);
        endtv = (TextView) source.findViewById(R.id.endtv);
        endtv2 = (TextView) source.findViewById(R.id.endtv2);
        endiv = (ImageView) source.findViewById(R.id.endiv);

        endBut.setText("Yes");
        endBut2.setText("No");
        endtv2.setText("Want to play again?");
        endiv.setImageResource(Game.imageIDs[logic.getAntalForkerteBogstaver()]);

        if (logic.erSidsteBogstavKorrekt() == true) {

            endtv.setText("You won the game! You used " + logic.getGuesses() + " guesses to guess the word: " + logic.getOrdet() + "!");

        } else if (logic.erSidsteBogstavKorrekt() == false) {

            endtv.setText("You lost the game! You couldn't guess the word: " + logic.getOrdet() + " in time!");

        }

        endBut.setOnClickListener(this);
        endBut2.setOnClickListener(this);

        return source;

    }

    @Override
    public void onClick(View v) {

        if (v == endBut) {

            getFragmentManager().popBackStackImmediate();

        } else if (v == endBut2) {

        }

    }
}
