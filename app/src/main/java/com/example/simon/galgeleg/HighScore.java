package com.example.simon.galgeleg;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

import static com.example.simon.galgeleg.Main_Activity.logic;

/**
 * Created by Simon on 11-11-2017.
 */

public class HighScore extends Fragment {

    private ListView hslv;
    static ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("HighScore", "the fragment was shown!!");

        View source = inflater.inflate(R.layout.activity_highscorelist, container, false);

        hslv = (ListView) source.findViewById(R.id.hslv);

        SimpleAdapter adapter = new SimpleAdapter(
                getContext(),
                logic.getHighscoreList(),
                R.layout.activity_highscoretext,
                new String[] {"word","wrong","player"},
                new int[] {R.id.word,R.id.wrong, R.id.player}

        );

        hslv.setAdapter(adapter);

        return source;
    }

}
