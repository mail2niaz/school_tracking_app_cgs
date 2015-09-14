package com.cgs.schoolbustracking.activities;

import com.cgs.schoolbustracking.R;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.Window;

/**
 * Launcher Activity 
 * @param
 * @return
 * @exception- InterruptedException
 * @see -Login.java
 * @since
 * @author-Ramya
 * @version-1.0 {@link-Login.java}
 */

public class SplashActivity extends Activity {

	SharedPreferences prefs;
	Handler mHandler = new Handler();
	Runnable r = new Runnable() {
		public void run() {
			updateUI();

		}
		
		//After completion of thread it calls Home page of the application
		private void updateUI() {
			// TODO Auto-generated method stub

		
			finish();
			startActivity(new Intent(SplashActivity.this, LoginActivity.class));
//			boolean logged=prefs.getBoolean(Common.LOGGEDIN, false);
//			String role=prefs.getString(Common.ROLE, "");
//			String name=prefs.getString(Common.USERNAME, "");
//
//
//			Intent i = new Intent();
//			i.putExtra("name",name);
//
//			if(!logged){
//			i.setClass(SplashActivity.this, Login.class);
//
//			}else{
//				if(role.equalsIgnoreCase(Common.SUPER_ADMIN))
//				{
//					i.setClass(SplashActivity.this, SuperAdminDashboard.class);
//				}else if(role.equalsIgnoreCase(Common.ADMIN))
//				{
//					i.setClass(SplashActivity.this, AdminDashboard.class);
//				}else if(role.equalsIgnoreCase(Common.SUPERVISOR))
//				{
//					i.setClass(SplashActivity.this, SupervisorDashboard.class);
//				}else{
//					i.setClass(SplashActivity.this, Login.class);
//				}
//			}
//
//			startActivity(i);

		}
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_splash);
		 prefs = PreferenceManager.getDefaultSharedPreferences(this);
	
		//Thread for splash screen to display
		Thread t = new Thread() {
			public void run() {

				{
					try {
						sleep(3000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				mHandler.post(r);
			}
		};
		t.start();

	}
}