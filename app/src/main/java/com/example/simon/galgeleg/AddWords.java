package com.example.simon.galgeleg;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

import static com.example.simon.galgeleg.Main_Activity.logic;

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
    Toast download;
    FileOutputStream files;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

    View source = inflater.inflate(R.layout.activity_words, container, false);

     //   files = getActivity().getFilesDir();

        CharSequence text = "Words downloading";
        int duration = Toast.LENGTH_SHORT;

        download = Toast.makeText(getContext(), text, duration);

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
                saveData();

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

            Thread thread = new Thread() {
                @Override
                public void run() {
                    try {
                        while(true) {
                            logic.hentOrdFraHjemmeside(url);
                            saveData();
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

            download.show();

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

    public void saveData() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        SharedPreferences.Editor editor = preferences.edit();
        for (int i = 0; i<logic.getMuligeOrd().size(); i++) {

            System.out.println(logic.getMuligeOrd().get(i));
            editor.putString("amount", String.valueOf(i));
            editor.putString(String.valueOf(i), logic.getMuligeOrd().get(i));
            editor.apply();

        }
    }


}
