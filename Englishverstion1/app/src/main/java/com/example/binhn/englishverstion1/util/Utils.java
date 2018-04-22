package com.example.binhn.englishverstion1.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by user on 15/04/2018.
 */

public class Utils {
    public static final String KEY_LANGUAGE = "lan";
    public static void setLanguagePref(Context context,String keyPref,String lang){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(keyPref, lang);
        editor.commit();
    }
    public static String getLang(Context context, String keyPref) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(keyPref, "");
    }
}
