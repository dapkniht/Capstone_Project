package com.example.capstone.Preferences;

import android.content.Context;
import android.content.SharedPreferences;


public class SharedPreference {
    static final String KEY_TOKEN = "User_Token";

    private final SharedPreferences preferences;

    public SharedPreference(Context context) {
        preferences = context.getSharedPreferences(KEY_TOKEN, Context.MODE_PRIVATE);
    }

    public void setKeyToken(String value) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(KEY_TOKEN, value);
        editor.apply();
    }
    public String getKeyToken() {
        return preferences.getString(KEY_TOKEN, "");
    }

    public void removeKeyToken() {
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove(KEY_TOKEN).commit();
        editor.apply();
    }
}
