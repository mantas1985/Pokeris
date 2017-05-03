package com.example.moksleivis.pokeris;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by moksleivis on 2017-05-03.
 */

public class Loginas {

    private static final String PREFERENCES_FILE_NAME = "com.example.moksleivis.pokeris";
    private static final String USERNAME_KEY = "username";
    private static final String PASSWORD_KEY = "password";
    private static final String REMEMBER_ME_KEY = "rememberMe";

    private SharedPreferences sharedPrefs;

    public Loginas(Context context) {
        this.sharedPrefs = context.getSharedPreferences(Loginas.PREFERENCES_FILE_NAME, Context.MODE_PRIVATE);
    }

    public String getPassword() {
        return this.sharedPrefs.getString(PASSWORD_KEY, "");
    }

    public void setPassword(String password) {
        this.sharedPrefs.edit().putString(PASSWORD_KEY, password).commit();
    }

    public String getUsername() {
        return this.sharedPrefs.getString(USERNAME_KEY, "");
    }

    public void setUsername(String username) {
        this.sharedPrefs.edit().putString(USERNAME_KEY, username).commit();
    }

    public boolean isRemembered() {
        return this.sharedPrefs.getBoolean(REMEMBER_ME_KEY, false);
    }

    public void setRemembered(boolean remembered) {
        this.sharedPrefs.edit().putBoolean(REMEMBER_ME_KEY, remembered).commit();
    }
}
