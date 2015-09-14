package com.cgs.schoolbustracking.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.cgs.schoolbustracking.R;
import com.cgs.schoolbustracking.adapters.MyNavigationAdapter;
import com.cgs.schoolbustracking.adapters.NavigationListAdapter;
import com.cgs.schoolbustracking.adapters.SearchStudentListAdapter;

import java.util.ArrayList;
import java.util.Arrays;

public class TripChooseActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnToSchool;
    Button btnToHome;

    Toolbar mToolbar;

//    private DrawerLayout mDrawerLayout;
//    private ListView mDrawerList;
//    private ActionBarDrawerToggle mDrawerToggle;
    NavigationListAdapter listAdapter;
    String[] firstLoginArray = new String[]{
            "Home", "Scan", "Logout"
    };
    ArrayList<String> firstList = new ArrayList<>(Arrays.asList(firstLoginArray));

    String TITLES[] = {"Home","Events","Mail","Shop","Travel"};
   // int ICONS[] = {R.drawable.ic_done_grey,R.drawable.ic_done_black_24dp,R.drawable.ic_done_lightgrey,R.drawable.ic_done_red,R.drawable.icon_login};

    //Similarly we Create a String Resource for the name and email in the header view
    //And we also create a int resource for profile picture in the header view

    String NAME = "Akash Bangad";
    String EMAIL = "akash.bangad@android4devs.com";
    int PROFILE = R.drawable.person;

    private Toolbar toolbar;                              // Declaring the Toolbar Object

    RecyclerView mRecyclerView;                           // Declaring RecyclerView
    MyNavigationAdapter mAdapter;                        // Declaring Adapter For Recycler View
    RecyclerView.LayoutManager mLayoutManager;            // Declaring Layout Manager as a linear layout manager
    DrawerLayout Drawer;                                  // Declaring DrawerLayout

    ActionBarDrawerToggle mDrawerToggle;                  // Declaring Action Bar Drawer Toggle




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_choose);

    /* Assinging the toolbar object ot the view
    and setting the the Action bar to our toolbar
     */
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);




        mRecyclerView = (RecyclerView) findViewById(R.id.RecyclerView); // Assigning the RecyclerView Object to the xml View

        mRecyclerView.setHasFixedSize(true);                            // Letting the system know that the list objects are of fixed size

        //mAdapter = new MyNavigationAdapter(TITLES,ICONS,NAME,EMAIL,PROFILE);
        mAdapter = new MyNavigationAdapter(TITLES,NAME,EMAIL,PROFILE); // Creating the Adapter of MyAdapter class(which we are going to see in a bit)
        // And passing the titles,icons,header view name, header view email,
        // and header view profile picture

        mRecyclerView.setAdapter(mAdapter);                              // Setting the adapter to RecyclerView

        mLayoutManager = new LinearLayoutManager(this);                 // Creating a layout Manager

        mRecyclerView.setLayoutManager(mLayoutManager);                 // Setting the layout Manager


        Drawer = (DrawerLayout) findViewById(R.id.DrawerLayout);        // Drawer object Assigned to the view
        mDrawerToggle = new ActionBarDrawerToggle(this,Drawer,toolbar,R.string.drawer_open,R.string.drawer_close){

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                // code here will execute once the drawer is opened( As I dont want anything happened whe drawer is
                // open I am not going to put anything here)
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                // Code here will execute once drawer is closed
            }



        }; // Drawer Toggle Object Made
        Drawer.setDrawerListener(mDrawerToggle); // Drawer Listener set to the Drawer toggle
        mDrawerToggle.syncState();               // Finally we set the drawer toggle sync State
        initUI();
    }

    /**
     * to initialise the UI
     */
    private void initUI() {

        btnToHome = (Button)findViewById(R.id.tripchoose_toschool_btn);
        btnToSchool= (Button)findViewById(R.id.tripchoose_tohome_btn);
        clickAction();
    }

    /**
     * to write click actions
     */
    private void clickAction() {
        btnToHome.setOnClickListener(this);
        btnToSchool.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.tripchoose_tohome_btn:

                startActivity(new Intent(TripChooseActivity.this, TripBusStopListActivity.class));
                break;
            case R.id.tripchoose_toschool_btn:

                startActivity(new Intent(TripChooseActivity.this, TripBusStopListActivity.class));

                break;
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
       // getMenuInflater().inflate(R.menu.menu_trip_choose, menu);
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
