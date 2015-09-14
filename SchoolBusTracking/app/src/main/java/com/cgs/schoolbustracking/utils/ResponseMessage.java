package com.cgs.schoolbustracking.utils;

/**
 * Created by ramya on 13/08/2015.
 */
public interface ResponseMessage {

    //Variables Used to handle Exception
    String JSONPARSING_EXCEPTION = "Some Response Mismatch Found";
    String SOCKETTIMEOUT_EXCEPTION = "Unable to Connect with Server";
    String SOCKET_EXCEPTION = "Connection Establishment Failed";
    String TIMEOUT_EXCEPTION = "Network Connection Timeout !";
    String UNABLE_TO_CONNECT = "Unable To Connect Now,Please Try Again Later !";
    String DATA_MISMATCH_FOUND = "Data Mismatch Found";
    String NO_RESPONSE = "No Response Found From Server";
    String UNKNOWN_HOST = "Unknown Host Found";
    String HTTP_HOST_EXCEPTION = "Http Host Exception";

}
