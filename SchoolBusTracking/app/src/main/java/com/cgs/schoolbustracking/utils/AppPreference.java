package com.cgs.schoolbustracking.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.location.Location;
import android.preference.Preference;
import android.util.Log;

import com.cgs.schoolbustracking.models.MyLocationModel;

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


    public void putLocation(Location location) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putFloat(LocationKeys.LAT.toString(), (float) location.getLatitude());
        editor.putFloat(LocationKeys.LNG.toString(), (float) location.getLongitude());
        editor.putFloat(LocationKeys.SPEED.toString(), (float) location.getSpeed());
        editor.putFloat(LocationKeys.ACCURACY.toString(), (float) location.getAccuracy());
        editor.putFloat(LocationKeys.ALTITUDE.toString(), (float) location.getAltitude());
        editor.putFloat(LocationKeys.BEARING.toString(), (float) location.getBearing());
        editor.putLong(LocationKeys.TIME.toString(), (long) location.getTime());
        Log.i(TAG, "Put String - key:" + location + " value:" + LocationKeys.LAT.toString());
        editor.commit();
    }

    public MyLocationModel getLocation() {
        MyLocationModel location = new MyLocationModel();
        location.setLatitude(sharedPreferences.getFloat(LocationKeys.LAT.toString(), 0));
        location.setLongitude(sharedPreferences.getFloat(LocationKeys.LNG.toString(), 0));
        location.setSpeed(sharedPreferences.getFloat(LocationKeys.SPEED.toString(), 0));
        location.setAccuracy(sharedPreferences.getFloat(LocationKeys.ACCURACY.toString(), 0));
        location.setAltitude(sharedPreferences.getFloat(LocationKeys.ALTITUDE.toString(), 0));
        location.setBearing(sharedPreferences.getFloat(LocationKeys.BEARING.toString(), 0));
        location.setTime(sharedPreferences.getLong(LocationKeys.TIME.toString(), 0));
        return location;
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

    public static enum LocationKeys {
        LAT,
        LNG,
        TIME,
        SPEED,
        ALTITUDE,
        BEARING,
        ACCURACY
    }

}
