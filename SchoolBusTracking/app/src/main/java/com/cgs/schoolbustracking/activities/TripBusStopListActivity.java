package com.cgs.schoolbustracking.activities;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.cgs.schoolbustracking.R;
import com.cgs.schoolbustracking.adapters.AreaBasedStudentListAdapter;
import com.cgs.schoolbustracking.adapters.TripBusstopNameLisAdapter;
import com.cgs.schoolbustracking.db.DatabaseHandler;
import com.cgs.schoolbustracking.models.BusStopNameModel;
import com.cgs.schoolbustracking.models.DriverModel;
import com.cgs.schoolbustracking.models.StudentDetailModel;
import com.cgs.schoolbustracking.models.VehicleModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class TripBusStopListActivity extends AppCompatActivity {
    //-------Widget--------
    RecyclerView recyclerView;
    private Toolbar mToolbar;
    TextView txtRouteBusStandName;


    TripBusstopNameLisAdapter adapter;
    List<StudentDetailModel> studentList;
    List<BusStopNameModel> busStopNameList;
    List<VehicleModel> vehicleList;
    List<DriverModel> driverList;

    DatabaseHandler db_student;

    List<StudentDetailModel> studentsListOfBusstop = new ArrayList<>();
    String toolbarRouteTitle;
    String toolbarRouteCount;

    private static final String TAG = TripBusStopListActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_bus_stop_list);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        getSupportActionBar().setTitle("My title");
        mToolbar.setTitleTextColor(getResources().getColor(R.color.white));
        //mToolbar.setTitle("TRIP SAID102");
        db_student=new DatabaseHandler(this);
        initUI();
    }


    /**
     * to initialise the UI
     */
    private void initUI() {

        final ActionBar ab = getSupportActionBar();
        // ab.setHomeAsUpIndicator(R.drawable.icon_nav);
        ab.setDisplayHomeAsUpEnabled(true);
        recyclerView = (RecyclerView)findViewById(R.id.student_list_recyclerview);
        txtRouteBusStandName  = (TextView)findViewById(R.id.studentlist_busstandname_txt);

        busStopNameList = new ArrayList<>();
        studentList = new ArrayList<>();

        InputStream is = getResources().openRawResource(R.raw.school_new);
        Writer writer = new StringWriter();
        char[] buffer = new char[1024];
        try {
            Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            int n;
            while ((n = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, n);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String jsonString = writer.toString();
        Log.v(TAG, "jsonString---->" + jsonString);


        if(db_student.getStudentsList().size()==0) {
            //processJson(jsonString);
            sampleProcessJson(jsonString);
        }
        else{
            studentList=db_student.getStudentsList();
           busStopNameList = db_student.getBusStopList();
        }
        setupList();
        mToolbar.setTitle("TRIP SAID102 ");
     //   txtRouteBusStandName.setText("Saidapet bus stand " + String.valueOf(db_student.getStudentsListWhereTrueOnBusstop().size()) + "/" + String.valueOf(studentsListOfBusstop.size()));


    }


    /**
     * for setting the values for the list using adapter
     */
    public void setupList() {


        studentList=db_student.getStudentsList();
        busStopNameList = db_student.getBusStopList();

        adapter = new TripBusstopNameLisAdapter(busStopNameList, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }


    private void processJson(String json){
        try {
            JSONObject jsonResponse = new JSONObject(json);

            Log.v(TAG,"jsonResponse---->"+jsonResponse.toString());

            JSONArray jsonArray = jsonResponse.getJSONArray("trip1");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                BusStopNameModel busStopNameModel = new BusStopNameModel();
                busStopNameModel.setBusStopName(jsonObject.getString("bustopname"));



                JSONArray studentArray = jsonObject.getJSONArray("students");

                for(int j=0;j<studentArray.length();j++){
                    StudentDetailModel student = new StudentDetailModel();
                    JSONObject studentJsonObj = studentArray.getJSONObject(j);
                    student.setName(studentJsonObj.getString("name"));
                    student.setBusStopName(jsonObject.getString("bustopname"));
                    student.setClasss(studentJsonObj.getString("class"));
                    studentList.add(student);
                    db_student.insertStudent(student);
                }
                busStopNameModel.setStudentDetailModelArrayList(studentList);
                Log.v(TAG, "busStopNameModel get---->" + busStopNameModel.getStudentDetailModelArrayList().size());
                busStopNameModel.setBusStopCount(String.valueOf(studentArray.length()));
                busStopNameList.add(busStopNameModel);
                db_student.insertBusStop(busStopNameModel);
            }
            //setupList();

        }catch (JSONException e){

        }

    }

    private void sampleProcessJson(String json){
        try {
            JSONObject jsonResponse = new JSONObject(json);

            Log.v(TAG,"jsonResponse---->"+jsonResponse.toString());
            JSONObject tripJsonObject = jsonResponse.getJSONObject("trip");
            Log.v(TAG,"trip---->"+tripJsonObject.toString());
            JSONObject vehicleJsonObject = tripJsonObject.getJSONObject("vehicle_details");
            VehicleModel vehicleModel = new VehicleModel();
            vehicleModel.setVehicleId(vehicleJsonObject.getString("vehicle_id"));
            vehicleModel.setVehicleNumber(vehicleJsonObject.getString("vehicle_num"));
            db_student.insertVehicle(vehicleModel);
            db_student.getVehicle();
            //vehicleList


            Log.v(TAG, "vehicle_details---->" + vehicleJsonObject.toString());
            JSONObject driverJsonObject = tripJsonObject.getJSONObject("driver_details");
            Log.v(TAG, "driver_details---->" + driverJsonObject.toString());
            DriverModel driverModel = new DriverModel();
            driverModel.setDriverId(driverJsonObject.getString("driver_id"));
            driverModel.setDriverName(driverJsonObject.getString("driver_name"));
            driverModel.setDriverNumber(driverJsonObject.getString("driver_num"));
            db_student.insertDriver(driverModel);
            db_student.getDriver();



            JSONArray jsonArray = tripJsonObject.getJSONArray("trip_details");
            Log.v(TAG,"trip_details---->"+jsonArray.toString());
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                BusStopNameModel busStopNameModel = new BusStopNameModel();
                busStopNameModel.setBusStopName(jsonObject.getString("bustopname"));



                JSONArray studentArray = jsonObject.getJSONArray("students");

                for(int j=0;j<studentArray.length();j++){
                    StudentDetailModel student = new StudentDetailModel();
                    JSONObject studentJsonObj = studentArray.getJSONObject(j);
                    student.setName(studentJsonObj.getString("name"));
                    student.setBusStopName(jsonObject.getString("bustopname"));
                    student.setClasss(studentJsonObj.getString("class"));
                    student.setDob(studentJsonObj.getString("dob"));
                    student.setGender(studentJsonObj.getString("gender"));
                    student.setBloodGroup(studentJsonObj.getString("bloodgroup"));
                    student.setParentName(studentJsonObj.getString("parentname"));
                    student.setParentNumber(studentJsonObj.getString("parentnumber"));
                    student.setFullAddress(studentJsonObj.getString("fulladdress"));
                    student.setDriverId(driverJsonObject.getString("driver_id"));
                    student.setVehicleId(vehicleJsonObject.getString("vehicle_id"));
                    studentList.add(student);
                    db_student.insertStudent(student);
                }
                busStopNameModel.setStudentDetailModelArrayList(studentList);
                Log.v(TAG, "busStopNameModel get---->" + busStopNameModel.getStudentDetailModelArrayList().size());
                busStopNameModel.setBusStopCount(String.valueOf(studentArray.length()));
                busStopNameList.add(busStopNameModel);
                Log.v(TAG, "busStopNameList---->" + busStopNameList.size());
                db_student.insertBusStop(busStopNameModel);
            }
            //setupList();

        }catch (JSONException e){

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
       // getMenuInflater().inflate(R.menu.menu_trip_bus_stop_list, menu);
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
