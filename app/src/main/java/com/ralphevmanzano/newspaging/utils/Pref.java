package com.ralphevmanzano.newspaging.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class Pref {
    private static final String FILE = Pref.class.getSimpleName() + "Pref";
    private static final String SEARCH = "Search";

    private SharedPreferences sharedPreferences;
    private static volatile Pref ourInstance;

    public static Pref getInstance(Context context) {
        if (ourInstance == null) {
            //if there is no instance available... create new one
            //synchronized to be thread safe, also only if null

            synchronized (Pref.class) {
                if (ourInstance == null) ourInstance = new Pref(context);
            }
        }

        return ourInstance;
    }

    private Pref(Context context) {
        sharedPreferences = context.getSharedPreferences(FILE, Context.MODE_PRIVATE);

        //Prevent form the reflection api.
        if (ourInstance != null){
            throw new RuntimeException("Use getInstance() method to get the single instance of this class.");
        }
    }

    public void setSearch(String search) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(SEARCH, search);
        editor.apply();
    }

    public String getSearch() {
        return sharedPreferences.getString(SEARCH, "");
    }
}
