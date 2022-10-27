package com.gist.demo.list.utils;


import android.content.Context;
import android.content.SharedPreferences;
/**
 * Created by KowsalyaM on 26/10/22.
 */

public class SharedPreference {

    public static final String PREFS_NAME = "HOT_GISTS_APP";
    public static final String FAVORITES = "HOT_GISTS";
    private static SharedPreference instance;

    public static SharedPreference getInstance() {
        if (instance == null) {
            instance = new SharedPreference();
        }
        return instance;
    }

    private SharedPreference() {
    }

    public void setFavourite(Context context, String key, boolean value) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public boolean getFavourite(Context context, String key) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
        return prefs.getBoolean(key, false);
    }

}
