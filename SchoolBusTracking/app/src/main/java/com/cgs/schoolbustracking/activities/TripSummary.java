package com.cgs.schoolbustracking.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.cgs.schoolbustracking.R;
import com.cgs.schoolbustracking.db.DatabaseHandler;

public class TripSummary extends AppCompatActivity {
    //--------widgets--------
    TextView txtTotalNoOfStudents;
    TextView txtNoOfStudentsBoarded;
    TextView txtNoOfStudentsMissed;
    TextView txtMessage;

    DatabaseHandler db_student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_summary);
        db_student=new DatabaseHandler(this);
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

        txtTotalNoOfStudents = (TextView)findViewById(R.id.summary_totalstudents_txt);
        txtNoOfStudentsBoarded = (TextView)findViewById(R.id.summary_studentsboarded_txt);
        txtNoOfStudentsMissed = (TextView)findViewById(R.id.summary_studentsmissed_txt);
        txtMessage= (TextView)findViewById(R.id.summary_studentsmessgae_txt);


        txtTotalNoOfStudents.setText("Total No. of students : " +db_student.getStudentsList().size()+"");
        txtNoOfStudentsBoarded.setText("No. of students boarded : " +db_student.getStudentsListWhereTrue().size()+"");
        txtNoOfStudentsMissed.setText("No. of students missed : "+(db_student.getStudentsList().size()-(db_student.getStudentsListWhereTrue().size()))+"");
        txtMessage.setText("Trip succesfully updated in server");
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