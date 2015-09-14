package com.cgs.schoolbustracking.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.Preference;
import android.util.Log;

/**
 * Created by ramya on 24/06/2015.
 */
public class AppPreference extends Preference {
    private static final String TAG = "AppPreference";

    /**
     * Singleton instance for AppPreference
     */
    public static AppPreference instance;
    private SharedPreferences sharedPreferences;

    private AppPreference(Context context) {
        super(context);
        sharedPreferences = context.getSharedPreferences("Preferences",
                Context.MODE_PRIVATE);
    }

    public static AppPreference getInstance(Context context) {
        if (instance == null)
            return new AppPreference(context);
        return instance;
    }

    //to put(save) the string value in preferences
    public void putString(StringKeys key, String value) {
        sharedPreferences.edit().putString(key.toString(), value).commit();
        Log.i(TAG, "Put String - key:" + key + " value:" + value);
    }


    //to get the saved string value from the preferences
    public String getString(StringKeys key) {
        return sharedPreferences.getString(key.toString(), null);
    }


    //to put(save) the int value in preferences
    public void putInt(IntKeys key, int value) {
        sharedPreferences.edit().putInt(key.toString(), value).commit();
        Log.i(TAG, "Put String - key:" + key + " value:" + value);
    }


    //to get the saved int value from the preferences
    public int getInt(IntKeys key) {
        return sharedPreferences.getInt(key.toString(), Integer.MIN_VALUE);
    }

    public static enum StringKeys {

        USER_NAME,

        EMPLOYEE_ID,

        ROLE,

        BRANCH_NAME

    }
    public static enum IntKeys {

        USER_ID

    }

}
