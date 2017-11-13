package com.example.simon.galgeleg;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.RequiresPermission;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import static com.example.simon.galgeleg.Main_Activity.logic;

/**
 * Created by Simon on 22-10-2017.
 */

public class AddWords extends Fragment implements View.OnClickListener {

    private EditText webet;
    private Button addWordBut;
    private Button webBut;
    private TextView wordtv;
    private String url;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

    View source = inflater.inflate(R.layout.activity_words, container, false);

        addWordBut = (Button) source.findViewById(R.id.addWordBut);

        webBut = (Button) source.findViewById(R.id.webBut);

        addWordBut.setOnClickListener(this);
        webBut.setOnClickListener(this);

        return source;

    }

    @Override
    public void onClick(View v) {

        if (v == addWordBut) {

            WriteWord fragment = new WriteWord();
            Bundle args = new Bundle();
            args.putString("state", "add");
            fragment.setArguments(args);

            getFragmentManager().beginTransaction()
                    .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                    .replace(R.id.fragments, fragment)
                    .addToBackStack(null)
                    .commit();

        } else if (v == webBut) {

            WriteWord fragment = new WriteWord();
            Bundle args = new Bundle();
            args.putString("state", "web");
            fragment.setArguments(args);

            getFragmentManager().beginTransaction()
                    .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                    .replace(R.id.fragments, fragment)
                    .addToBackStack(null)
                    .commit();

        }

    }

    public void saveData() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        SharedPreferences.Editor editor = preferences.edit();
        for (int i = 0; i<logic.getMuligeOrd().size(); i++) {

            editor.putString("amount", String.valueOf(i));
            editor.putString(String.valueOf(i), logic.getMuligeOrd().get(i));
            editor.apply();

        }
    }


}
