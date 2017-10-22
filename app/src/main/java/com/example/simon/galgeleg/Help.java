package com.example.simon.galgeleg;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by Simon on 20-10-2017.
 */

public class Help extends Fragment {

    private WebView wv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View source = inflater.inflate(R.layout.activity_help, container, false);

       wv = (WebView) source.findViewById(R.id.wv);
        String help = "https://en.wikipedia.org/wiki/Hangman_(game)";
        WebSettings webSettings = wv.getSettings();
        webSettings.setJavaScriptEnabled(true);

        wv.setWebViewClient(new WebViewClient());

        wv.loadUrl(help);

        return source;
    }
}

