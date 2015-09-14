package com.cgs.schoolbustracking.activities;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.cgs.schoolbustracking.R;
import com.cgs.schoolbustracking.models.StudentDetailModel;

public class StudentDetailActivity extends AppCompatActivity {
    //------------Widgets--------
    TextView txtName;
    TextView txtClass;
    TextView txtSection;
    TextView txtDOB;
    TextView txtGender;
    TextView txtBloodGroup;
    TextView txtParentName;
    TextView txtParentNumber;
    TextView txtFullAddress;
    TextView txtBusStop;
    ImageView imgStudent;

    Bundle bundle;
    StudentDetailModel item;
    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_detail);

        initUI();
    }

    /**
     * to initialise the UI
     */
    private void initUI() {

        bundle = getIntent().getExtras();
        item = (StudentDetailModel)bundle.getSerializable("item");
        if(item!=null){
            Log.d("", "item value name" + item.getName());
            Log.d("", "item value name" + item.getGender());
             name = item.getName();
        }

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
       toolbar.setTitleTextColor(getResources().getColor(R.color.white));

        txtName = (TextView)findViewById(R.id.detail_name_txt);
        txtClass = (TextView)findViewById(R.id.detail_class_txt);
        txtDOB = (TextView)findViewById(R.id.detail_dob_txt);
        txtGender = (TextView)findViewById(R.id.detail_gender_txt);
        txtBloodGroup = (TextView)findViewById(R.id.detail_Bloodgroup_txt);
        txtParentName = (TextView)findViewById(R.id.detail_parentname_txt);
        txtParentNumber = (TextView)findViewById(R.id.detail_parentnumber_txt);
        txtFullAddress = (TextView)findViewById(R.id.detail_Fulladdress_txt);
        txtBusStop = (TextView)findViewById(R.id.detail_busstop_txt);

        imgStudent= (ImageView)findViewById(R.id.image);

        int resId = getResources().getIdentifier(item.getName().toLowerCase(), "drawable", getPackageName());
        Drawable image = getResources().getDrawable(resId);
        imgStudent.setImageDrawable(image);

        txtName.setText(item.getName());
        txtClass.setText(item.getClasss());
        txtDOB.setText(item.getDob());
        txtGender.setText(item.getGender());
        txtBloodGroup.setText(item.getBloodGroup());
        txtParentName.setText(item.getParentName());
        txtParentNumber.setText(item.getParentNumber());
        txtFullAddress.setText(item.getFullAddress());
        txtBusStop.setText(item.getBusStopName());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_student_detail, menu);
        return true;
    }

    private void call(){
          /*  Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + item.getMobile()));
                startActivity(intent);*/
    }

    private void email(){
     /*   Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("message/rfc822");
                i.putExtra(Intent.EXTRA_EMAIL  , new String[]{item.getEmailId()});
                i.putExtra(Intent.EXTRA_SUBJECT, "subject of email");
                i.putExtra(Intent.EXTRA_TEXT   , "body of email");
                try {
                    startActivity(Intent.createChooser(i, "Send mail..."));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(CustomerDetailActivity.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                }*/

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
