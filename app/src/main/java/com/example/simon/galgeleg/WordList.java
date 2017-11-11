package com.example.simon.galgeleg;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;

import static android.provider.AlarmClock.EXTRA_MESSAGE;
import static com.example.simon.galgeleg.Main_Activity.logic;

/**
 * Created by Simon on 22-10-2017.
 */

public class WordList extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener {

    private ListView lv;
    private ArrayList<String> possibleWords;
    private ArrayAdapter adapter;
    private Button delBut1, delBut2;
    private TextView deltv;
    private int pos;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View source = inflater.inflate(R.layout.activity_wordlist, container, false);

        lv = (ListView) source.findViewById(R.id.wordList);
        deltv = (TextView) source.findViewById(R.id.deltv);
        delBut1 = (Button) source.findViewById(R.id.delBut1);
        delBut2 = (Button) source.findViewById(R.id.delBut2);

        deltv.setText("Click on a word you wish to delete:");

        makeAdapter();

        lv.setOnItemClickListener(this);
        delBut1.setOnClickListener(this);
        delBut2.setOnClickListener(this);

        return source;

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        pos = position;
        lv.setVisibility(View.GONE);
        delBut1.setVisibility(View.VISIBLE);
        delBut2.setVisibility(View.VISIBLE);
        deltv.setText("Are you sure you wish to delete " + possibleWords.get(position).toString() + "?");

    }

    @Override
    public void onClick(View v) {

        if (v == delBut1) {

            logic.deleteWord(pos);
            delBut1.setVisibility(View.GONE);
            delBut2.setVisibility(View.GONE);
            lv.setVisibility(View.VISIBLE);
            makeAdapter();
            deltv.setText("Click on a word you wish to delete:");

        } else if (v == delBut2) {

            delBut1.setVisibility(View.GONE);
            delBut2.setVisibility(View.GONE);
            lv.setVisibility(View.VISIBLE);
            deltv.setText("Click on a word you wish to delete:");

        }

    }

    public void makeAdapter() {

        possibleWords = logic.getMuligeOrd();

        String[] wordArray = possibleWords.toArray(new String[possibleWords.size()]);

        adapter = new ArrayAdapter<String>(getActivity(), R.layout.activity_textview, R.id.wordtv,wordArray);

        lv.setAdapter(adapter);

    }
}
