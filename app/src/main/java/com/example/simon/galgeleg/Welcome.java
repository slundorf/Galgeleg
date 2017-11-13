package com.example.simon.galgeleg;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Simon on 20-10-2017.
 */

public class Welcome extends Fragment implements Runnable {

    Handler handler = new Handler();
    TextView tv1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("Welcome", "the fragment was shown!");

        ImageView iv = new ImageView(getActivity());
        iv.setImageResource(R.drawable.forkert6);
        iv.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.intro_anim));

        if (savedInstanceState == null) {
            handler.postDelayed(this, 3000);
        }

        return iv;
    }

    public void run() {

        MainMenu fragment = new MainMenu();

        getFragmentManager().beginTransaction()
                .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                .replace(R.id.fragments, fragment)
                .addToBackStack(null)
                .commit();
    }
}