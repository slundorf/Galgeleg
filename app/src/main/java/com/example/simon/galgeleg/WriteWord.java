package com.example.simon.galgeleg;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Objects;

import static com.example.simon.galgeleg.Main_Activity.logic;

/**
 * Created by Simon on 13-11-2017.
 */

public class WriteWord extends Fragment implements View.OnClickListener {

    EditText writeet;
    Button writeBut;
    String state;
    String word;
    Toast download;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("WriteWord", "the fragment was shown!!");

        View source = inflater.inflate(R.layout.activity_writeword, container, false);

        CharSequence text = "Words downloading";
        int duration = Toast.LENGTH_SHORT;

        download = Toast.makeText(getContext(), text, duration);

        writeBut = (Button) source.findViewById(R.id.writeBut);
        writeet = (EditText) source.findViewById(R.id.writeet);

        state = getArguments().getString("state", String.valueOf(0));

        if (Objects.equals(state, "web")) {
            writeet.setHint("Enter website you wish to get words from");
        }

        writeBut.setOnClickListener(this);

        return source;
    }

    @Override
    public void onClick(View v) {

        word = writeet.getText().toString();

        if (Objects.equals(state, "web")) {

            if (!URLUtil.isHttpsUrl(word)) {
                writeet.setError("Please start your URL with https://");
            } else {

                Thread thread = new Thread() {
                    @Override
                    public void run() {
                        try {
                            while (true) {
                                logic.hentOrdFraHjemmeside(word);
                                logic.saveWords(getContext());
                                return;
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                };

                thread.start();

                download.show();

                FragmentManager fm = getFragmentManager();
                fm.popBackStackImmediate();

            }

        } else {

            if (word.length() < 3) {

                writeet.setError("Enter a word to continue!");

            } else if (word.length() >= 3) {

                if (Objects.equals(state, "game")) {

                    Game fragment = new Game();
                    Bundle args = new Bundle();
                    args.putString("word", word);
                    fragment.setArguments(args);

                    getFragmentManager().beginTransaction()
                            .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                            .replace(R.id.fragments, fragment)
                            .addToBackStack(null)
                            .commit();

                } else if (Objects.equals(state, "add")) {

                    ArrayList<String> possibleWords = logic.getMuligeOrd();

                    for (int i = 0; i < possibleWords.size(); i++) {
                        if (word.equals(possibleWords.get(i))) {

                            writeet.setError(word + " is already in the game");

                            return;
                        }
                    }

                    logic.tilføjOrd(word);
                    logic.saveWords(getContext());
                    FragmentManager fm = getFragmentManager();
                    fm.popBackStackImmediate();

                }
            }

        }

    }

}
