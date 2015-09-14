package com.cgs.schoolbustracking.utils;

/**
 * Created by ramya on 20/07/2015.
 */
public class AppConstants {

//    public static final String BASE_URL = "http://appserver.constient.com/kycdiss/";
//    //public static final String APK_VERSION = "3400";
//    public static final String LOGIN_URL = "api/Login/Authentication";
//    public static final String PAN_VALIDATION_URL = "api/Registration/KRAExistingClientValidation";
//    public static final String REF_GENERATION_URL = "api/Registration/ReferenceNumberGeneration";
//    public static final String CLIENT_DETAIL_SUBMIT_URL = "api/Registration/ClientInfoRegistration";

   public static final String BASE_URL = "http://appserver.constient.com/KYCDiss/api/";

   // appserver UAT
    //public static final String BASE_URL = "https://express.edelbusiness.in/kycdiss/api/";

    public static final String IMAGE_BASE_URL = "http://appserver.constient.com/KYCDiss";
    public static final String LOGIN_URL = BASE_URL + "Login/Authentication";
    public static final String GET_REF_API = BASE_URL + "QRCode/GetClientInfolistByEmployeeId";

    public static final String UPDATE_REF_API = BASE_URL + "QRCode/updateQRDocumentTrackingStatusList";

    public static final String UPDATE_SIGNIMAGE_API = BASE_URL + "QRCode/updateQRFormCollectStatus";
    public static final String REJECT_REF_API = BASE_URL + "QRCode/updateQRDocumentTrackingStatusList";
    public static final String CUSTOMER_API = BASE_URL + "Profile/GetProfileInfoByReferenceNumber";
    public static final String CUSTOMER_UPDATE =BASE_URL +"DocumentTracking/UpdateDocumentTrackingByStatus";
    public static final String APK_VERSION = "4102";
    public static final String GET_PICKUPCODE_API =BASE_URL +"QRCode/ValidatePickupCode";
    public static final String GET_VERIFICATIONIMAGE_API =BASE_URL +"QRCode/getClientImagesbyReferenceNumber";
    public static final String RESEND_API =BASE_URL +"QRCode/SendPickupCode";
    public static final String GET_BRANCHEMPLIST_API =BASE_URL +"QRCode/getEmployeelistByBranch";
    public static final String POST_POD_API =BASE_URL +"QRCode/updatePODStatus";
    public static final String GET_VIEW_POD_API =BASE_URL +"QRCode/getPODDetails";
    public static final String GET_VIEW_POD_LIST_API =BASE_URL +"QRCode/getPODNumber";

    public static final String GET_VIEW_REF_HISTORY_API =BASE_URL +"QRCode/getCustomerHistory";

    //http://appserver.constient.com/KYCDISS/Help/Api/POST-api-QRCode-SendPickupCode
    public static final int ASSIGNED_STATUS_ID = 1;
    public static final int ACCEPTED_STATUS_ID = 2;
    public static final int DECLINED_STATUS_ID = 3;
    public static final int SIGNED_STATUS_ID = 4;
    public static final int DISCRPENCY_STATUS_ID = 5;
    public static final int BRANCH_VERIFIED_STATUS_ID = 6;
    public static final int BRANCH_REASSIGNED_STATUS_ID = 7;
    public static final int HO_EBO_COMPLETED_STATUS_ID = 8;
    public static final int BRANCH_OR_EBO_REASSIGNEDTORM_STATUS_ID = 9;
    public static final int DROPPED_BEFORE_SIGNED = 10;
    public static final int BRANCH_REVIEWED_REJECTED_SENDTOBO = 11;
    public static final int BRANCH_REVIEWED_REASSIGNED_TORM = 12;
    public static final int DROPPED_FROM_EBO = 13;
    public static final int BRANCH_POD_CREATE_STATUS = 14;

    public static final String LOGIN_RM = "Agent";
    public static final String LOGIN_BRANCH = "SrAgent";
    public static final String STATUS = "QRStageStatusInfoId";

    //for passing values for scan
    public static final String INTENT_ACTOIN_FROM = "intent from";
    public static final String KEY_SCAN_ID = "scan id";
    public static final String KEY_SCAN_LIST = "scan ids list";

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
