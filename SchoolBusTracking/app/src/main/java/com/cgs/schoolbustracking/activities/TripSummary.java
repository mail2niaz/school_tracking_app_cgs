package com.cgs.schoolbustracking.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.cgs.schoolbustracking.R;
import com.cgs.schoolbustracking.db.DatabaseHandler;
import com.cgs.schoolbustracking.models.BusStopNameModel;
import com.cgs.schoolbustracking.models.StudentDetailModel;

import java.util.ArrayList;
import java.util.List;

public class TripSummary extends AppCompatActivity {
    //--------widgets--------
    TextView txtBusId;
    TextView txtTime;
    TextView txtFrom;
    TextView txtTo;
    TextView txtTotalNoOfStudents;
    TextView txtNoOfStudentsBoarded;
    TextView txtNoOfStudentsMissed;
    TextView txtMessage;




    DatabaseHandler db_student;

    Bundle bundle;
    BusStopNameModel mItem;
    List<BusStopNameModel> summaryList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_summary);
        db_student=new DatabaseHandler(this);

        bundle = getIntent().getExtras();
        mItem = (BusStopNameModel)bundle.getSerializable("item");
        if(mItem!=null){
            //Log.d("", "item value name" + mItem.get);
            Log.d("", "item value name" + mItem.getBusStopCount());
            Log.d("", "item value name in tripsummary" + mItem.getFromArea()+mItem.getToArea()+mItem.getPlannedStartTime());
        }
        initUI();
    }

    /**
     * to initialise the UI
     */
    private void initUI() {
        

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
         txtBusId = (TextView)findViewById(R.id.tripsummary_bus_txt);
         txtTime = (TextView)findViewById(R.id.tripsummary_time_txt);
         txtFrom = (TextView)findViewById(R.id.tripsummary_from_txt);
         txtTo = (TextView)findViewById(R.id.tripsummary_to_txt);
        txtTotalNoOfStudents = (TextView)findViewById(R.id.tripsummary_total_txt);
        txtNoOfStudentsBoarded = (TextView)findViewById(R.id.tripsummary_boarded_txt);
        txtNoOfStudentsMissed = (TextView)findViewById(R.id.tripsummary_missed_txt);
        //txtMessage= (TextView)findViewById(R.id.summary_studentsmessgae_txt);

        txtBusId.setText(mItem.getVehicleId());
        txtTime.setText(mItem.getPlannedStartTime()+" - "+mItem.getPlannedEndTime());
        txtFrom.setText(mItem.getFromArea());
        txtTo.setText(mItem.getPlannedEndTime());
        txtTotalNoOfStudents.setText(db_student.getStudentsList().size()+"");
        txtNoOfStudentsBoarded.setText(db_student.getStudentsListWhereTrue().size()+"");
        txtNoOfStudentsMissed.setText((db_student.getStudentsList().size()-(db_student.getStudentsListWhereTrue().size()))+"");
        //txtMessage.setText("Trip succesfully updated in server");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_trip_summary, menu);
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
