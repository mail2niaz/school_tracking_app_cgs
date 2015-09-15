package com.cgs.schoolbustracking.services;

import android.app.Service;
import android.content.Intent;
import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationManager;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.cgs.schoolbustracking.sms.SMSSender;
import com.cgs.schoolbustracking.utils.AppConstants;
import com.cgs.schoolbustracking.utils.AppController;
import com.cgs.schoolbustracking.utils.AppPreference;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;

import org.json.JSONObject;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * Using location settings.
 * <p/>
 * Uses the {@link com.google.android.gms.location.SettingsApi} to ensure that the device's system
 * settings are properly configured for the app's location needs. When making a request to
 * Location services, the device's system settings may be in a state that prevents the app from
 * obtaining the location data that it needs. For example, GPS or Wi-Fi scanning may be switched
 * off. The {@code SettingsApi} makes it possible to determine if a device's system settings are
 * adequate for the location request, and to optionally invoke a dialog that allows the user to
 * enable the necessary settings.
 * <p/>
 * This sample allows the user to request location updates using the ACCESS_FINE_LOCATION setting
 * (as specified in AndroidManifest.xml). The sample requires that the device has location enabled
 * and set to the "High accuracy" mode. If location is not enabled, or if the location mode does
 * not permit high accuracy determination of location, the activity uses the {@code SettingsApi}
 * to invoke a dialog without requiring the developer to understand which settings are needed for
 * different Location requirements.
 */
public class LocationSchedulerService extends Service implements
        ConnectionCallbacks,
        OnConnectionFailedListener,
        LocationListener, GpsStatus.Listener,
        ResultCallback<LocationSettingsResult> {

    protected static final String TAG = "location-settings";

    /**
     * Constant used in the location settings dialog.
     */
    protected static final int REQUEST_CHECK_SETTINGS = 0x1;

    /**
     * The desired interval for location updates. Inexact. Updates may be more or less frequent.
     */
    public static final long UPDATE_INTERVAL_IN_MILLISECONDS = 10000;

    /**
     * The fastest rate for active location updates. Exact. Updates will never be more frequent
     * than this value.
     */
    public static final long FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS = 10000;

    //    public static final long FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS =
//            UPDATE_INTERVAL_IN_MILLISECONDS / 2;
    // Keys for storing activity state in the Bundle.
    protected final static String KEY_REQUESTING_LOCATION_UPDATES = "requesting-location-updates";
    protected final static String KEY_LOCATION = "location";
    protected final static String KEY_LAST_UPDATED_TIME_STRING = "last-updated-time-string";

    /**
     * Provides the entry point to Google Play services.
     */
    protected GoogleApiClient mGoogleApiClient;
    protected LocationSettingsRequest mLocationSettingsRequest;
    /**
     * Stores parameters for requests to the FusedLocationProviderApi.
     */
    protected LocationRequest mLocationRequest;

    /**
     * Stores the types of location services the client is interested in using. Used for checking
     * settings to determine if the device has optimal location settings.
     */

    /**
     * Represents a geographical location.
     */
    protected Location mCurrentLocation;

    LocationManager mLocationManager;

    //private Dbadapter dbadapter;

    int syncinterval_ms = -1;
    String loginId;

    Handler timerHandler = new Handler();
    Runnable timerRunnable;

    // Binder given to clients
    private final IBinder binder = new LocalBinder();
    // Registered callbacks
    private ServiceCallbacks serviceCallbacks;

    // Class used for the client Binder.
    public class LocalBinder extends Binder {
        public LocationSchedulerService getService() {
            // Return this instance of MyService so clients can call public methods
            return LocationSchedulerService.this;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(getApplicationContext(), "Inside Service On onCreate", Toast.LENGTH_LONG).show();
        Log.d(TAG, "Niaz::inside Service onCreate method");
        buildGoogleApiClient();
//        dbadapter = new Dbadapter(this);
//        dbadapter.open();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent.getExtras() != null) {
           // Bundle b = intent.getExtras();
            syncinterval_ms = /*b.getInt("syncinterval")*/20 * 1000;
            //loginId = b.getString("loginId");
            Log.d(TAG, "Niaz::inside Service onStartCommand method,syncinterval_ms=" + syncinterval_ms + ",loginId=" + loginId);


            timerRunnable = new Runnable() {

                @Override
                public void run() {
                    Log.d(TAG, "Niaz::inside timerRunnable @" + new Date());
                    if (AppConstants.APP_CONNECTIVITY_STATUS) {
//                    makeAPIServiceCall();

                    } else {
//                    makeSMSServiceCall();
                        timerHandler.postDelayed(this, syncinterval_ms);
                    }
                }
            };
            timerHandler.postDelayed(timerRunnable, 0);
        }
        return super.onStartCommand(intent, flags, startId);
    }

    private void makeSMSServiceCall() {
        Toast.makeText(getApplicationContext(), "Calling SMS Service since Offline", Toast.LENGTH_LONG).show();
        SMSSender smsSender = new SMSSender();
        smsSender.sendSMS("94860901018", "Hi Niaz", getApplicationContext());
    }

    @Override
    public void onConnected(Bundle bundle) {
        Log.d(TAG, "Niaz::inside Service onConnected method");
        startLocationUpdates();
//        makeAPIServiceCall();

    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.d(TAG, "Niaz::inside Service onConnectionSuspended method");
    }

    @Override
    public void onLocationChanged(Location location) {
        Log.d(TAG, "Nia::inside Service onLocationChanged method" + location.getLatitude() + "," + location.getLongitude() + "," + location.getTime());
        //dbadapter.insertDetails("1", location.getLatitude() + "," + location.getLongitude(), location.getSpeed(), location.getAccuracy(), location.getAltitude(), location.getBearing(), location.getTime(), "PENDING", 0);
        AppPreference.getInstance(getApplicationContext()).putLocation(location);
        AppConstants.myLocation = location;
        if (serviceCallbacks != null)
            serviceCallbacks.StartListenLocation(location);
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.d(TAG, "Niaz::inside Service onConnectionFailed method");
    }


    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    public void setCallbacks(ServiceCallbacks callbacks) {
        serviceCallbacks = callbacks;
    }

    protected synchronized void buildGoogleApiClient() {
        Log.i(TAG, "Building GoogleApiClient");
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
        createLocationRequest();
    }


    protected void createLocationRequest() {
        mLocationRequest = new LocationRequest();

        // Sets the desired interval for active location updates. This interval is
        // inexact. You may not receive updates at all if no location sources are available, or
        // you may receive them slower than requested. You may also receive updates faster than
        // requested if other applications are requesting location at a faster interval.
        mLocationRequest.setInterval(UPDATE_INTERVAL_IN_MILLISECONDS);

        // Sets the fastest rate for active location updates. This interval is exact, and your
        // application will never receive updates faster than this value.
        mLocationRequest.setFastestInterval(FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS);

        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }

    protected void startLocationUpdates() {
        // The final argument to {@code requestLocationUpdates()} is a LocationListener
        // (http://developer.android.com/reference/com/google/android/gms/location/LocationListener.html).
        LocationServices.FusedLocationApi.requestLocationUpdates(
                mGoogleApiClient, mLocationRequest, this);
//        mLocationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
//        mLocationManager.addGpsStatusListener(this);
        checkLocationSettings();
    }

    @Override
    public void onGpsStatusChanged(int event) {
        Log.d(TAG, "Niaz::ionGpsStatusChanged method");
        switch (event) {
            case GpsStatus.GPS_EVENT_STARTED:
                Log.d(TAG, "Niaz::inside onGpsStatusChanged GPS_EVENT_STARTED method");
                break;
            case GpsStatus.GPS_EVENT_STOPPED:
                Log.d(TAG, "Niaz::inside onGpsStatusChanged GPS_EVENT_STOPPED method");
                break;
        }
    }

    protected void checkLocationSettings() {
        buildLocationSettingsRequest();
        PendingResult<LocationSettingsResult> result =
                LocationServices.SettingsApi.checkLocationSettings(
                        mGoogleApiClient,
                        mLocationSettingsRequest
                );
        result.setResultCallback(this);
    }

    protected void buildLocationSettingsRequest() {
        //niaz - code to ask User to enable GPS
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder();
        builder.addLocationRequest(mLocationRequest);
        mLocationSettingsRequest = builder.build();
    }

    @Override
    public void onResult(LocationSettingsResult locationSettingsResult) {
        final Status status = locationSettingsResult.getStatus();
        switch (status.getStatusCode()) {
            case LocationSettingsStatusCodes.SUCCESS:
                Log.i(TAG, "All location settings are satisfied.");
//                startLocationUpdates();
                break;
            case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                Log.i(TAG, "Location settings are not satisfied. Show the user a dialog to" +
                        "upgrade location settings ");

//                try {
//                    // Show the dialog by calling startResolutionForResult(), and check the result
//                    // in onActivityResult().
//                    status.startResolutionForResult(LocationSettingsActivity.this, REQUEST_CHECK_SETTINGS);
//                } catch (IntentSender.SendIntentException e) {
//                    Log.i(TAG, "PendingIntent unable to execute request.");
//                }
                break;
            case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                Log.i(TAG, "Location settings are inadequate, and cannot be fixed here. Dialog " +
                        "not created.");
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(getApplicationContext(), "Inside Service OnDestroy", Toast.LENGTH_LONG).show();
        timerHandler.removeCallbacks(timerRunnable);
        Log.d(TAG, "Niaz::inside Service OnDestroy method");
        LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);

        //dbadapter.close();
    }

    private void makeAPIServiceCall() {
        // Tag used to cancel the request
        Toast.makeText(getApplicationContext(), "Calling API Service since Online", Toast.LENGTH_LONG).show();
        String tag_json_obj = "json_obj_req";

        String url = "http://api.androidhive.info/volley/person_object.json";


        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, url, (String) null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, "JsonObjectRequest response = " + response.toString());
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("name", "Androidhive");
                params.put("email", "abc@androidhive.info");
                params.put("password", "password123");

                return params;
            }

        };

// Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
    }


}