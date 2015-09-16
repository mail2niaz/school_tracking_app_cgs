package com.cgs.schoolbustracking.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.cgs.schoolbustracking.R;
import com.cgs.schoolbustracking.adapters.AreaBasedStudentListAdapter;
import com.cgs.schoolbustracking.db.DatabaseHandler;
import com.cgs.schoolbustracking.models.BusStopNameModel;
import com.cgs.schoolbustracking.models.StudentDetailModel;

import java.util.ArrayList;
import java.util.List;

public class AreaBasedStudentListActivity extends AppCompatActivity implements View.OnClickListener{

    //-------Widget--------
    RecyclerView recyclerView;
    private Toolbar mToolbar;
    TextView txtRouteBusStandName;


    AreaBasedStudentListAdapter adapter;
    List<StudentDetailModel> studentList;
    DatabaseHandler db_student;

    List<StudentDetailModel> studentsList = new ArrayList<>();
    List<StudentDetailModel> studentsListOfBusstop = new ArrayList<>();
    String toolbarRouteTitle;
    String toolbarRouteCount;
    ArrayList<String> list = new ArrayList<>();



    Bundle bundle;
    BusStopNameModel mItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area_based_student_list);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        getSupportActionBar().setTitle("My title");
        mToolbar.setTitleTextColor(getResources().getColor(R.color.white));
        //mToolbar.setTitle("TRIP SAID102");
        db_student=new DatabaseHandler(this);
        studentsList = new ArrayList<>();
        bundle = getIntent().getExtras();
        mItem = (BusStopNameModel)bundle.getSerializable("busstopname");
        list= (ArrayList<String>)bundle.getSerializable("busstopnames");



         if(mItem!=null){
            Log.d("", "item value name" + mItem.getBusStopName());
            Log.d("", "item value name" + mItem.getBusStopCount());
             Log.d("", "item value name in area based" + mItem.getFromArea()+mItem.getToArea()+mItem.getPlannedStartTime());
            if(db_student.getStudentsList().size()!=0)
            {
                studentsList =  db_student.getStudentsList();
            }else{
                studentsList = mItem.getStudentDetailModelArrayList();
            }

            Log.d("", "item value student" + studentsList.size());
            //name = item.getName();
        }


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

        clickActions();

        studentsListOfBusstop = db_student.getArrayStudentsListOfParticularBusStop(mItem.getBusStopName(), list);


        /*for(int i=0;i<list.size();i++){
            studentsListOfBusstop = db_student.getStudentsListOfParticularBusStop(list.get(i));
        }*/

        mToolbar.setTitle("TRIP SAID102 " + String.valueOf(db_student.getStudentsListWhereTrue().size()) + "/" + String.valueOf(studentsList.size()));
        txtRouteBusStandName.setText(mItem.getBusStopName()+" bus stand " + String.valueOf(db_student.getStudentsListWhereTrueOnBusstop(mItem.getBusStopName()).size()) + "/" + String.valueOf(studentsListOfBusstop.size()));
        setupList();

    }


    /**
     * for setting the values for the list using adapter
     */
    public void setupList() {

            adapter = new AreaBasedStudentListAdapter(studentsListOfBusstop, this,mItem.getBusStopName(),list);
            recyclerView.setAdapter(adapter);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    /**
     * to get the count from adapter to set in toolbar
     */
    public void getCount(int i,int j/*,List<StudentDetailModel> studentsListOfBusstop*/){
        studentsListOfBusstop = db_student.getArrayStudentsListOfParticularBusStop(mItem.getBusStopName(), list);
        mToolbar.setTitle("TRIP SAID102 " + i + "/" + String.valueOf(studentsList.size()));
//        Toast.makeText(this,
//                "studentsListOfBusstop.size()" +studentsListOfBusstop.size(), Toast.LENGTH_LONG).show();
        txtRouteBusStandName.setText(mItem.getBusStopName() + " bus stand " + j + "/" + String.valueOf(studentsListOfBusstop.size()));
    }


    private void clickActions(){
        txtRouteBusStandName.setOnClickListener(this);
    }


    @Override
    protected void onResume() {
        super.onResume();
//        studentsListOfBusstop = db_student.getArrayStudentsListOfParticularBusStop(item.getBusStopName(), list);
//        adapter.notifyDataSetChanged();
//        setupList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_area_based_student_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                //showDialog();
                finish();
                return true;
            case R.id.action_settings:

                Intent i = new Intent(AreaBasedStudentListActivity.this, TripSummary.class);
                i.putExtra("item", mItem);
                startActivity(i);
                return true;
            case R.id.action_search:
                startActivity(new Intent(AreaBasedStudentListActivity.this, SearchStudentsListActivity.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * to show the alert whether to close the trip or not
     */
    private void showDialog(){
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(AreaBasedStudentListActivity.this);
        alertDialog.setTitle("Login");
        alertDialog.setMessage("Do you want to cancel the trip?");
        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
               finish();
            }
        });
        alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alertDialog.show();
    }

    @Override
    public void onBackPressed() {
       super.onBackPressed();
        // showDialog();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.studentlist_busstandname_txt:
                //startActivity(new Intent(AreaBasedStudentListActivity.this, Trip.class));
                finish();

        }
    }
}
