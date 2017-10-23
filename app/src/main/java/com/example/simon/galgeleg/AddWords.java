package com.example.simon.galgeleg;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.util.ArrayList;
import static com.example.simon.galgeleg.MainMenu.logic;

/**
 * Created by Simon on 22-10-2017.
 */

public class AddWords extends Fragment implements View.OnClickListener {

    private EditText wordet;
    private EditText webet;
    private Button wordBut3;
    private Button wordBut4;
    private Button wordBut5;
    private Button webBut;
    private TextView wordtv;
    private String url;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

    View source = inflater.inflate(R.layout.activity_words, container, false);

        wordet = (EditText) source.findViewById(R.id.wordet);

        webet = (EditText) source.findViewById(R.id.webet);

        wordBut3 = (Button) source.findViewById(R.id.wordBut3);

        wordBut4 = (Button) source.findViewById(R.id.wordBut4);

        wordBut5 = (Button) source.findViewById(R.id.wordBut5);

        webBut = (Button) source.findViewById(R.id.webBut);

        wordtv = (TextView) source.findViewById(R.id.wordtv);

        wordet.setHint("Enter the word you wish to add");

        webet.setHint("Enter website you want to add words from");

        wordBut3.setOnClickListener(this);
        wordBut4.setOnClickListener(this);
        wordBut5.setOnClickListener(this);
        webBut.setOnClickListener(this);

        return source;

    }

    @Override
    public void onClick(View v) {

        if (v == wordBut3) {

            String word = wordet.getText().toString();
            ArrayList<String> possibleWords = logic.getMuligeOrd();

            for(int i = 0; i<possibleWords.size(); i++) {
                if (word.equals(possibleWords.get(i).toString())) {

                    wordet.setError(word + " is already in the game");

                    return;
                }
            }

                logic.tilføjOrd(word);

                updateScreen();

        } else if (v == wordBut4) {

            wordBut3.setVisibility(View.VISIBLE);
            wordet.setVisibility(View.VISIBLE);
            webet.setVisibility(View.VISIBLE);
            webBut.setVisibility(View.VISIBLE);
            wordtv.setVisibility(View.GONE);
            wordBut4.setVisibility(View.GONE);
            wordBut5.setVisibility(View.GONE);

        } else if (v == wordBut5) {

            getFragmentManager().popBackStackImmediate();

        } else if (v == webBut) {

            url = webet.getText().toString();

           // if ()

            Thread thread = new Thread() {
                @Override
                public void run() {
                    try {
                        while(true) {
                            sleep(1000);
                            logic.hentOrdFraHjemmeside(url);
                            return;
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };

            thread.start();

            updateScreen();

        }

    }

    public void updateScreen() {

        wordBut3.setVisibility(View.GONE);
        wordet.setVisibility(View.GONE);
        webBut.setVisibility(View.GONE);
        webet.setVisibility(View.GONE);
        wordBut4.setVisibility(View.VISIBLE);
        wordBut5.setVisibility(View.VISIBLE);
        wordtv.setVisibility(View.VISIBLE);


    }


}
