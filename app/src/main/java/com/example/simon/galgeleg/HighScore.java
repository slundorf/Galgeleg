package com.example.simon.galgeleg;

import android.database.MatrixCursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

import static com.example.simon.galgeleg.Main_Activity.logic;

/**
 * Created by Simon on 11-11-2017.
 */

public class HighScore extends Fragment {

    private ListView hslv;
    private ArrayList<String> possibleWords;
    private ArrayAdapter adapter;
    static ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("HighScore", "the fragment was shown!!");

        View source = inflater.inflate(R.layout.activity_highscorelist, container, false);

        hslv = (ListView) source.findViewById(R.id.hslv);

        SimpleAdapter adapter = new SimpleAdapter(
                getContext(),
                list,
                R.layout.activity_highscoretext,
                new String[] {"word","wrong","player"},
                new int[] {R.id.word,R.id.wrong, R.id.player}

        );

        populateList();


        hslv.setAdapter(adapter);


        return source;
    }


    public void makeAdapter() {

        possibleWords = logic.getMuligeOrd();

        String[] wordArray = possibleWords.toArray(new String[possibleWords.size()]);

        adapter = new ArrayAdapter<String>(getActivity(), R.layout.activity_highscorelist, R.id.word,wordArray);

        hslv.setAdapter(adapter);

    }

    public void populateList() {

        System.out.println("TEST TEST TEST");

        for (int i = 0; i<logic.getHighscoreList().size(); i++) {
            System.out.println("TEST2 TEST2 TEST2");
         //   if(!(logic.getHighscoreList().get(i).get("word").equals(logic.getOrdet()))) {
                System.out.println("TEST3 TEST3 TEST3");
                list = logic.getHighscoreList();
           //     list.add(logic.getHighscoreList().get(i));
          //  }
        }

    }

    public void addData() {
        HashMap<String,String> temp = new HashMap<String,String>();
        temp.put("word", logic.getOrdet());
        temp.put("wrong", "200.00$");
        temp.put("player", "Silver, Grey, Black");
        list.add(temp);
    }

}
