package com.cgs.schoolbustracking.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;

import org.apache.http.HttpStatus;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ramya on 27/07/2015.
 * this class is for posting and getting data from webservices
 */
public class WebserviceUtits {
    static ProgressDialog pDialog;
    static Context c;
    JSONObject jsonObject;

    public static final String TAG = WebserviceUtits.class.getSimpleName();
    private static String tag_json_obj = "jobj_req";

    WebServiceListener mListener;

    /**
     * method to post json values to the webservices
     * @param jsonObject
     * @param mListener
     * @param url
     */
    public static void postJson(JSONObject jsonObject,final WebServiceListener mListener,String url,final int id) {
        HttpsTrustManager.allowAllSSL();

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,url,jsonObject,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, response.toString());
                       // processPostResult(response);
                        if(mListener!=null)
                      mListener.webServiceSuccess(id,response);
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                NetworkResponse networkResponse = error.networkResponse;
                if (networkResponse != null && networkResponse.statusCode == HttpStatus.SC_UNAUTHORIZED) {
                    // HTTP Status Code: 401 Unauthorized
                }
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                if(mListener!=null)
                mListener.webServiceError(id, error.getMessage());
            }
        }){

            /**
             * Passing some request headers
             * */
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("name", "Androidhive");
                params.put("email", "abc@androidhive.info");
                params.put("pass", "password123");

                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq,
                tag_json_obj);

        // Cancelling request
        // ApplicationController.getInstance().getRequestQueue().cancelAll(tag_json_obj);
    }

    /**
     * method to get the json values from webservices
     * @param mListener
     */
    public static void getJson(final WebServiceListener mListener,String url,int id){

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,url,(String)null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, response.toString());
                        if(mListener!=null)
                            mListener.webServiceSuccess(1,response);
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());

                if(mListener!=null)
                    mListener.webServiceError(1, error.getMessage());

            }
        });


        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
    }


    /**
     * listener to get the output values
     */
    public static interface WebServiceListener {

        /**
         * This method will be called when service returns success
         */
        public void webServiceSuccess(int id, Object serviceOutput);

        /**
         * This method will be called when service returns error msg
         */
        public void webServiceError(int id, String errorMsg);
    }



}
