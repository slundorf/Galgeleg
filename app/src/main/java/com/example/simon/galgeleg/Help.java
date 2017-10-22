package com.example.simon.galgeleg;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

/**
 * Created by Simon on 20-10-2017.
 */

public class Help extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View source = inflater.inflate(R.layout.activity_help, container, false);



        String help = "<html><body>"
                + "<h1>Hj&aelig;lpesk&aelig;rm</h1>"
                + "<p>Her kunne st&aring; noget hj&aelig;lp.<br>Men den er ikke skrevet endnu.</p>";

        WebView wv = new WebView(getActivity());
        wv.loadData(help, "text/html", null);
        return wv;
    }
}