package com.example.simon.galgeleg;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.simon.galgeleg.Logic.Galgelogik;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Simon on 22-10-2017.
 */

public class WordList extends Fragment implements View.OnClickListener {

    private Galgelogik logic;
    private ListView lv;
    private ArrayList<String> possibleWords;

    public WordList(Galgelogik l) {
        logic = l;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View source = inflater.inflate(R.layout.activity_wordlist, container, false);

        lv = (ListView) source.findViewById(R.id.wordList);

        possibleWords = logic.getMuligeOrd();

        String[] wordArray = possibleWords.toArray(new String[possibleWords.size()]);

        ArrayAdapter adapter = new ArrayAdapter<String>(getActivity(), R.layout.activity_textview, R.id.wordtv,wordArray);

        lv.setAdapter(adapter);

        return source;

    }

    @Override
    public void onClick(View v) {

    }
}
