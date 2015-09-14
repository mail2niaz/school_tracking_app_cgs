package com.cgs.schoolbustracking.utils;



import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.Fragment;
import android.telephony.TelephonyManager;


public class NetworkUtils extends Fragment {

	/**
	 * @param context
	 * @return true if network connected else false
	 */
	public static final boolean checkNetworkState(Context context) {
		ConnectivityManager cm = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);

		NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
		boolean isConnected = activeNetwork != null
				&& activeNetwork.isConnectedOrConnecting();

		return isConnected;
	}

	/**
	 * Get the network info
	 * 
	 * @param context
	 * @return
	 */
	public static NetworkInfo getNetworkInfo(Context context) {
		ConnectivityManager cm = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		return cm.getActiveNetworkInfo();
	}

	/**
	 * Check if there is any connectivity
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isConnected(Context context) {
		NetworkInfo info = NetworkUtils.getNetworkInfo(context);
		return (info != null && info.isConnected());
	}

	/**
	 * Check if there is any connectivity to a Wifi network
	 * 
	 * @param context
	 * @param //type
	 * @return
	 */
	public static boolean isConnectedWifi(Context context) {
		NetworkInfo info = NetworkUtils.getNetworkInfo(context);
		return (info != null && info.isConnected() && info.getType() == ConnectivityManager.TYPE_WIFI);
	}

	/**
	 * Check if there is any connectivity to a mobile network
	 * 
	 * @param context
	 * @param //type
	 * @return
	 */
	public static boolean isConnectedMobile(Context context) {
		NetworkInfo info = NetworkUtils.getNetworkInfo(context);
		return (info != null && info.isConnected() && info.getType() == ConnectivityManager.TYPE_MOBILE);
	}

	/**
	 * Check if there is fast connectivity
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isConnectedFast(Context context) {
		NetworkInfo info = NetworkUtils.getNetworkInfo(context);
		return (info != null && info.isConnected() && NetworkUtils
				.isConnectionFast(info.getType(), info.getSubtype()));
	}

	/**
	 * Check if the connection is fast
	 * 
	 * @param type
	 * @param subType
	 * @return
	 */
	public static boolean isConnectionFast(int type, int subType) {
		if (type == ConnectivityManager.TYPE_WIFI) {
			return true;
		} else if (type == ConnectivityManager.TYPE_MOBILE) {
			switch (subType) {
			case TelephonyManager.NETWORK_TYPE_1xRTT:
				return false; // ~ 50-100 kbps
			case TelephonyManager.NETWORK_TYPE_CDMA:
				return false; // ~ 14-64 kbps
			case TelephonyManager.NETWORK_TYPE_EDGE:
				return false; // ~ 50-100 kbps
			case TelephonyManager.NETWORK_TYPE_EVDO_0:
				return true; // ~ 400-1000 kbps
			case TelephonyManager.NETWORK_TYPE_EVDO_A:
				return true; // ~ 600-1400 kbps
			case TelephonyManager.NETWORK_TYPE_GPRS:
				return false; // ~ 100 kbps
			case TelephonyManager.NETWORK_TYPE_HSDPA:
				return true; // ~ 2-14 Mbps
			case TelephonyManager.NETWORK_TYPE_HSPA:
				return true; // ~ 700-1700 kbps
			case TelephonyManager.NETWORK_TYPE_HSUPA:
				return true; // ~ 1-23 Mbps
			case TelephonyManager.NETWORK_TYPE_UMTS:
				return true; // ~ 400-7000 kbps
				/*
				 * Above API level 7, make sure to set android:targetSdkVersion
				 * to appropriate level to use these
				 */
			case TelephonyManager.NETWORK_TYPE_EHRPD: // API level 11
				return true; // ~ 1-2 Mbps
			case TelephonyManager.NETWORK_TYPE_EVDO_B: // API level 9
				return true; // ~ 5 Mbps
			case TelephonyManager.NETWORK_TYPE_HSPAP: // API level 13
				return true; // ~ 10-20 Mbps
			case TelephonyManager.NETWORK_TYPE_IDEN: // API level 8
				return false; // ~25 kbps
			case TelephonyManager.NETWORK_TYPE_LTE: // API level 11
				return true; // ~ 10+ Mbps
				// Unknown
			case TelephonyManager.NETWORK_TYPE_UNKNOWN:
			default:
				return false;
			}
		} else {
			return false;
		}
	}
	
	// to show alert message
//		public static void alertMsg(final Context context){
//			AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
//					context);
//
//			// set title
//			alertDialogBuilder.setTitle("ION");
//
//			// set dialog message
//			alertDialogBuilder
//					.setMessage("Please check your Internet connection.")
//					.setCancelable(false)
//					.setPositiveButton(
//							"Okay",
//							new DialogInterface.OnClickListener() {
//								public void onClick(
//										DialogInterface dialog,
//										int id) {
//									dialog.cancel();
//
//								}
//							});
//
//
//			// create alert dialog
//			AlertDialog alertDialog = alertDialogBuilder
//					.create();
//
//			// show it
//			alertDialog.show();
//
//			//to change the divider and title color
//			 int dividerId = alertDialog.getContext().getResources().getIdentifier("android:id/titleDivider", null, null);
//		     View divider = alertDialog.findViewById(dividerId);
//		     int currentapiVersion = Build.VERSION.SDK_INT;
//		     if(currentapiVersion< Build.VERSION_CODES.LOLLIPOP) {
//		    	 if (divider != null)
//		            divider.setBackgroundColor(context.getResources().getColor(R.color.app_color));
//		        }
//		        if(currentapiVersion == Build.VERSION_CODES.LOLLIPOP) {
//
//		        }
//		     int textViewId = alertDialog.getContext().getResources().getIdentifier("android:id/alertTitle", null, null);
//		     TextView tv = (TextView) alertDialog.findViewById(textViewId);
//		     tv.setTextColor(context.getResources().getColor(R.color.app_color));
//
//
//		}

}
