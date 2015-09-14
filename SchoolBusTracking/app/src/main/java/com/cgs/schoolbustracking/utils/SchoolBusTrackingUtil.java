package com.cgs.schoolbustracking.utils;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by ramya on 03/09/2015.
 */
public class SchoolBusTrackingUtil {

    Context mcontext;

    public SchoolBusTrackingUtil(Context mcontext){
        this.mcontext = mcontext;
    }

    public void showToast(String toastMsg){
        Toast.makeText(mcontext, toastMsg, Toast.LENGTH_SHORT).show();
    }

    public void printLog(String tag,String logMsg){
        Log.v(tag, logMsg);
    }

}
