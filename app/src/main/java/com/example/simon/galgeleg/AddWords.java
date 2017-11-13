package com.example.simon.galgeleg;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Simon on 22-10-2017.
 */

public class AddWords extends Fragment implements View.OnClickListener {

    private Button addWordBut;
    private Button webBut;

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

}
