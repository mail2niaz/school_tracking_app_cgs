package com.cgs.schoolbustracking.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.cgs.schoolbustracking.R;
import com.cgs.schoolbustracking.db.DatabaseHandler;
import com.cgs.schoolbustracking.utils.AppConstants;
import com.cgs.schoolbustracking.utils.CrashErrorReporter;
import com.cgs.schoolbustracking.utils.NetworkUtils;
import com.cgs.schoolbustracking.utils.SchoolBusTrackingUtil;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {

    //--------widgets-------
    EditText edUsername;
    EditText edPassword;
    Button btnLogin;

    //-------String values-----
    private String strUsername;
    private String strPassword;

    //------Json object--------
    HashMap<String, Object> params;
    JSONObject jsonObject;


    //------validation---------
    Pattern ps;
    Matcher ms;
    boolean bs;

    DatabaseHandler db;
    public static Display display;
    public static LoginActivity mLoginActivity;

    SchoolBusTrackingUtil schoolBusTrackingUtil;

    public static final String TAG = LoginActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initUI();
    }

    /**
     * to initialise the UI
     */
    private void initUI() {
        CrashErrorReporter mCrashErrorReporter = CrashErrorReporter.getInstance();

        // Activate the ErrorReporter
        mCrashErrorReporter.Init(getApplicationContext());
        mCrashErrorReporter.CheckCrashErrorAndSendMail(getApplicationContext());

        schoolBusTrackingUtil = new SchoolBusTrackingUtil(this);

        edUsername = (EditText)findViewById(R.id.username_edt);
        edPassword = (EditText)findViewById(R.id.password_edt);
        btnLogin = (Button)findViewById(R.id.login_btn);

        edUsername.setText("fjd");
        edPassword.setText("fjd");
        clickActions();
    }

    /**
     * to write click actions
     */
    private void clickActions() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(edUsername.getText().toString().trim().length()!=0 && edPassword.getText().toString().trim().length()!=0) {
                    strUsername = edUsername.getText().toString();
                    strPassword = edPassword.getText().toString();
                    startActivity(new Intent(LoginActivity.this, TripChooseActivity.class));

                    if (NetworkUtils.isConnected(LoginActivity.this)) {

                        params = new HashMap<String, Object>();
                        params.put("UserName", strUsername);
                        params.put("Password", strPassword);
                        params.put("APKVersion", AppConstants.APK_VERSION);
                        jsonObject = new JSONObject(params);

//                        Log.d("TAG", "value =" + jsonObject.toString());
//
//                        progressDialog = ProgressDialog.show(LoginActivity.this, "",
//                                "Please wait..", true);
//                        WebserviceUtits.postJson(jsonObject, webServiceListener,AppConstants.LOGIN_URL, 1);
                    }else{
//                        //cipUtil.showToast("Please check your internet connection..");
//                        LoginModel loginModel =  db.getLoginData();
//                        if(loginModel!=null){
//                            if(strUsername.equals(loginModel.getUserName()) && strPassword.equals(loginModel.getPassword())){
//
//                                Log.v(TAG,"db output success----->"+strUsername+" "+strPassword);
//                                if(loginModel.getRoleName().equalsIgnoreCase(AppConstants.LOGIN_RM)) {
//
//                                    startActivity(new Intent(LoginActivity.this, RMHomeActivity.class));
//                                }else{
//                                    startActivity(new Intent(LoginActivity.this, BranchHomeActivity.class));
//                                }
//                                edUserName.setText("");
//                                edPassword.setText("");
//                                schoolBusTrackingUtil.showToast("successfully logged in");
//                            }else{
//                                schoolBusTrackingUtil.showToast("Please provide valid credentials..");
//                            }
//                        }


                    }
                }else{
                    final AlertDialog.Builder alertDialog = new AlertDialog.Builder(LoginActivity.this);
                    alertDialog.setTitle("Login");
                    alertDialog.setMessage("Please fill the credentials");
                    alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    alertDialog.show();
                }
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
