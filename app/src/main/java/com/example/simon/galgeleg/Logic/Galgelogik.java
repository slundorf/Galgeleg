package com.example.simon.galgeleg.Logic;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

public class Galgelogik {
  private ArrayList<String> muligeOrd = new ArrayList<String>();
  private String ordet;
  private ArrayList<String> brugteBogstaver = new ArrayList<String>();
  private String synligtOrd;
  private int guesses;
  private int antalForkerteBogstaver;
  private boolean sidsteBogstavVarKorrekt;
  private boolean spilletErVundet;
  private boolean spilletErTabt;
  private ArrayList<HashMap<String,String>> highscorelist = new ArrayList<HashMap<String,String>>();

  public ArrayList<String> getBrugteBogstaver() {
    return brugteBogstaver;
  }

  public String getSynligtOrd() {
    return synligtOrd;
  }

  public String getOrdet() {
    return ordet;
  }

  public ArrayList<String> getMuligeOrd() {

    return muligeOrd;

  }

  public int getGuesses() {
    return guesses;
  }

  public int getAntalForkerteBogstaver() {
    return antalForkerteBogstaver;
  }

  public boolean erSidsteBogstavKorrekt() {
    return sidsteBogstavVarKorrekt;
  }

  public boolean erSpilletVundet() {
    return spilletErVundet;
  }

  public boolean erSpilletTabt() {
    return spilletErTabt;
  }

  public boolean erSpilletSlut() {
    return spilletErTabt || spilletErVundet;
  }

  public void setOrdet(String ord) {

    ordet = ord;

  }

  public void deleteWord(int pos) {

    muligeOrd.remove(pos);

  }

  public void setHighscorelist(String word, String wrong, String player) {

    // Function used only to load high score list upon starting the app

    HashMap<String,String> score = new HashMap<String,String>();
    score.put("word", word);
    score.put("wrong", wrong);
    score.put("player", player);
    highscorelist.add(score);

  }

  public void addHighscore(String name) {

    boolean replace = false;

    // If a previous record for the word exists, for loop will replace amount of guesses and the player name with the new record set
    for (int i = 0; i<highscorelist.size(); i++) {
      if (highscorelist.get(i).get("word").equals(ordet)) {
        highscorelist.get(i).replace("wrong", String.valueOf(guesses));
        highscorelist.get(i).replace("player", name);
        replace = true;
      }
    }

    if (!replace) {
      HashMap<String, String> score = new HashMap<String, String>();
      score.put("word", ordet);
      score.put("wrong", String.valueOf(guesses));
      score.put("player", name);
      highscorelist.add(score);
    }

  }

  public ArrayList<HashMap<String,String>> getHighscoreList() {

    // Comparator that runs through and sorts the high score list alphabetically
    Collections.sort(highscorelist, new Comparator<HashMap<String,String>>() {
      public int compare(HashMap<String, String> map1, HashMap<String, String> map2) {
        return map1.get("word").compareTo(map2.get("word"));
      }
    });

    return highscorelist;
  }

  public Galgelogik() {
    guesses = 0;
    muligeOrd.add("bil");
    muligeOrd.add("computer");
    muligeOrd.add("programmering");
    muligeOrd.add("motorvej");
    muligeOrd.add("busrute");
    muligeOrd.add("gangsti");
    muligeOrd.add("skovsnegl");
    muligeOrd.add("solsort");
    nulstil();
  }

  public void nulstil() {
    brugteBogstaver.clear();
    guesses = 0;
    antalForkerteBogstaver = 0;
    spilletErVundet = false;
    spilletErTabt = false;
    ordet = muligeOrd.get(new Random().nextInt(muligeOrd.size()));
    opdaterSynligtOrd();
  }

  public void opdaterSynligtOrd() {
    synligtOrd = "";
    spilletErVundet = true;
    for (int n = 0; n < ordet.length(); n++) {
      String bogstav = ordet.substring(n, n + 1);
      if (brugteBogstaver.contains(bogstav)) {
        synligtOrd = synligtOrd + bogstav;
      } else {
        synligtOrd = synligtOrd + " _";
        spilletErVundet = false;
      }
    }
  }

  public void gætBogstav(String bogstav) {
    if (bogstav.length() != 1) return;
    System.out.println("Der gættes på bogstavet: " + bogstav);
    if (brugteBogstaver.contains(bogstav)) return;
    if (spilletErVundet || spilletErTabt) return;

    guesses++;

    brugteBogstaver.add(bogstav);

    if (ordet.contains(bogstav)) {
      sidsteBogstavVarKorrekt = true;
      System.out.println("Bogstavet var korrekt: " + bogstav);
    } else {
      // Vi gættede på et bogstav der ikke var i ordet.
      sidsteBogstavVarKorrekt = false;
      System.out.println("Bogstavet var IKKE korrekt: " + bogstav);
      antalForkerteBogstaver = antalForkerteBogstaver + 1;
      if (antalForkerteBogstaver == 6) {
        spilletErTabt = true;
      }
    }
    opdaterSynligtOrd();
  }

  public void gætOrd(String ord) {
    System.out.println("Der gættes på ordet: " + ord);
    if (brugteBogstaver.contains(ord)) return;
    if (spilletErVundet || spilletErTabt) return;

    guesses++;

    brugteBogstaver.add(ord);

    if (ordet.equals(ord)) {
      sidsteBogstavVarKorrekt = true;
      spilletErVundet = true;
      synligtOrd = ordet;
      return;
    } else {
      // Forkerte ord
      sidsteBogstavVarKorrekt = false;
      System.out.println("Ordet var IKKE korrekt: " + ord);
      antalForkerteBogstaver = antalForkerteBogstaver + 1;
      if (antalForkerteBogstaver == 6) {
        spilletErTabt = true;
      }
    }
    opdaterSynligtOrd();
  }

  public void logStatus() {
    System.out.println("---------- ");
    System.out.println("- ordet (skult) = " + ordet);
    System.out.println("- synligtOrd = " + synligtOrd);
    System.out.println("- forkerteBogstaver = " + antalForkerteBogstaver);
    System.out.println("- brugeBogstaver = " + brugteBogstaver);
    if (spilletErTabt) System.out.println("- SPILLET ER TABT");
    if (spilletErVundet) System.out.println("- SPILLET ER VUNDET");
    System.out.println("---------- ");
  }


  public static String hentUrl(String url) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(new URL(url).openStream()));
    StringBuilder sb = new StringBuilder();
    String linje = br.readLine();
    while (linje != null) {
      sb.append(linje + "\n");
      linje = br.readLine();
    }
    return sb.toString();
  }

  public void tilføjOrd(String ord) {

    for (int i = 0; i<muligeOrd.size(); i++) {
      if (muligeOrd.get(i).equals(ord)) {
        return;
      }
    }

    muligeOrd.add(ord);

  }

  public void hentOrdFraHjemmeside(String url) throws Exception {
    String data = hentUrl(url);

    data = data.substring(data.indexOf("<body")). // fjern headere
            replaceAll("<.+?>", " ").toLowerCase(). // fjern tags
            replaceAll("&#198;", "æ"). // erstat HTML-tegn
            replaceAll("&#230;", "æ"). // erstat HTML-tegn
            replaceAll("&#216;", "ø"). // erstat HTML-tegn
            replaceAll("&#248;", "ø"). // erstat HTML-tegn
            replaceAll("&oslash;", "ø"). // erstat HTML-tegn
            replaceAll("&#229;", "å"). // erstat HTML-tegn
            replaceAll("[^a-zæøå]", " "). // fjern tegn der ikke er bogstaver
            replaceAll(" [a-zæøå] "," "). // fjern 1-bogstavsord
            replaceAll(" [a-zæøå][a-zæøå] "," "); // fjern 2-bogstavsord

    System.out.println("data = " + data);
    System.out.println("data = " + Arrays.asList(data.split("\\s+")));

    muligeOrd.addAll(new HashSet<String>(Arrays.asList(data.split(" "))));

    System.out.println("muligeOrd = " + muligeOrd);
    nulstil();
  }

  public void saveWords(Context ctx) {
    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(ctx);
    SharedPreferences.Editor editor = preferences.edit();

    // Runs through the entire word list
    for (int i = 0; i<muligeOrd.size(); i++) {

      editor.putString("amountw", String.valueOf(i)); // Saves the amount of entries on the word list
      editor.putString("word" + String.valueOf(i), muligeOrd.get(i)); // Saves each entry with keys based on their position on the list
      editor.apply();

    }
  }

  public void saveHighScore(Context ctx) {
    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(ctx);
    SharedPreferences.Editor editor = preferences.edit();

    // Runs through the entire high score list
    for (int i = 0; i<highscorelist.size(); i++) {

      editor.putString("amounths", String.valueOf(i)); // Saves the amount of entries to the high score list
      editor.putString("word" + String.valueOf(i), highscorelist.get(i).get("word")); // Saves each entry of word with a key based on its position on the list
      editor.putString("wrong" + String.valueOf(i), highscorelist.get(i).get("wrong")); // Saves each entry of wrong with a key based on its position on the list
      editor.putString("player" + String.valueOf(i), highscorelist.get(i).get("player")); // Saves each entry of player with a key based on its position on the list
      editor.apply();

    }
  }

}
