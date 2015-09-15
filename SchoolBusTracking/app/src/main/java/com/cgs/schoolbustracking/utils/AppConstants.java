package com.cgs.schoolbustracking.utils;

import android.location.Location;

/**
 * Created by ramya on 20/07/2015.
 */
public class AppConstants {

    //for location
    public static boolean APP_CONNECTIVITY_STATUS = true;
    public static Location myLocation = null;


   public static final String BASE_URL = "http://appserver.constient.com/KYCDiss/api/";

   // appserver UAT
    //public static final String BASE_URL = "https://express.edelbusiness.in/kycdiss/api/";


    public static final String APK_VERSION = "4102";

    public static final String LOGIN_RM = "Agent";

    public static String removeNull(Object input) {
        String value = "";
        if (input == null) {

        } else if (input.toString().trim().equalsIgnoreCase("null")) {

        } else {
            value = input.toString().trim();
        }
        return value;
    }

    public static String returnStatus(int statusNumber) {
        String status;
        switch (statusNumber) {

            case 1:  status = "ASSIGNED";
                break;
            case 2:  status = "ACCEPTED";
                break;
            case 3:  status = "DECLINED";
                break;
            case 4:  status = "SIGNED";
                break;
            case 5:  status = "DISCRPENCY";
                break;
            case 6:  status = "BRANCH VERIFIED";
                break;
            case 7:  status = "BRANCH REASSIGNED";
                break;
            case 8:  status = "HO EBO COMPLETED";
                break;
            case 9:  status = "BRANCH OR EBO REASSIGNEDTORM";
                break;
            case 10: status = "DROPPED BEFORE SIGNED";
                break;
            case 11: status = "BRANCH REVIEWED REJECTED SENDTOBO";
                break;
            case 12: status = "BRANCH REVIEWED REASSIGNED TORM";
                break;
            case 13: status = "DROPPED FROM EBO";
                break;
            case 14: status = "BRANCH POD CREATE STATUS";
                break;
            default: status = "";
                break;
        }

        return status;
    }

}
