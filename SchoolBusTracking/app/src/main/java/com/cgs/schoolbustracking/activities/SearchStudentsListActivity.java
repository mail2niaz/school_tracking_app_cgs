package com.cgs.schoolbustracking.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cgs.schoolbustracking.R;
import com.cgs.schoolbustracking.adapters.AreaBasedStudentListAdapter;
import com.cgs.schoolbustracking.adapters.SearchStudentListAdapter;
import com.cgs.schoolbustracking.db.DatabaseHandler;
import com.cgs.schoolbustracking.models.StudentDetailModel;

import java.util.ArrayList;
import java.util.List;

public class SearchStudentsListActivity extends AppCompatActivity {
    TextWatcher mSearchTw;
    LinearLayout txtNoItem;

    EditText edStudentFilter;
    RecyclerView recyclerView;


    DatabaseHandler db_student;


    List<StudentDetailModel> studentsList = new ArrayList<>();
    SearchStudentListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_students_list);
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

        recyclerView = (RecyclerView)findViewById(R.id.search_student_list_recyclerview);
        txtNoItem = (LinearLayout)findViewById(R.id.lin_noitem);
        edStudentFilter = (EditText)findViewById(R.id.student_search_edt);
        //studentList = new ArrayList<>();

        studentsList=db_student.getStudentsList();
        setupList();

    }

    /**
     * for setting the values for the list using adapter
     */
    public void setupList() {

        adapter = new SearchStudentListAdapter(studentsList, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        search();
    }

    /**
     * to search the particular list item
     */
    private void search() {

            mSearchTw = new TextWatcher() {

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                    ArrayList<StudentDetailModel> items = new ArrayList<>();
                    studentsList.clear();
                    studentsList = db_student.getStudentsList();
                    for (int i = 0; i < studentsList.size(); i++) {

                        if (studentsList.get(i).getName().toLowerCase().contains(s.toString())||studentsList.get(i).getClasss().toLowerCase().contains(s.toString())) {

                            StudentDetailModel studentDetailModel = new StudentDetailModel();

                            studentDetailModel.setName(studentsList.get(i).getName());
                            studentDetailModel.setParentNumber(studentsList.get(i).getParentNumber());
                            studentDetailModel.setId(studentsList.get(i).getId());
                            studentDetailModel.setClasss(studentsList.get(i).getClasss());
                            studentDetailModel.setStudentCheckIn(studentsList.get(i).isStudentCheckIn());

//                        studentDetailModel.setName(studentsList.get(i).getName());
//                        studentDetailModel.setParentNumber(studentsList.get(i).getParentNumber());
//                        studentDetailModel.setClasss(studentsList.get(i).getClasss());
//                        studentDetailModel.setGender(studentsList.get(i).getGender());
//                        studentDetailModel.setSection(studentsList.get(i).getSection());
//                        studentDetailModel.setBloodGroup(studentsList.get(i).getBloodGroup());
//                        studentDetailModel.setDob(studentsList.get(i).getDob());
//                        studentDetailModel.setParentName(studentsList.get(i).getParentName());
//                        studentDetailModel.setFullAddress(studentsList.get(i).getParentNumber());
//                        studentDetailModel.setBusStopName(studentsList.get(i).getBusStopName());
//                        studentDetailModel.setStudentCheckIn(studentsList.get(i).isStudentCheckIn());
//                        studentDetailModel.setId(studentsList.get(i).getId());


                            items.add(studentDetailModel);


                        }
                    }


                    if (items.size() != 0) {
                        recyclerView.setVisibility(View.VISIBLE);
                        adapter = new SearchStudentListAdapter(items, SearchStudentsListActivity.this);
                        recyclerView.setAdapter(adapter);
                        recyclerView.setItemAnimator(new DefaultItemAnimator());
                        recyclerView.setLayoutManager(new LinearLayoutManager(SearchStudentsListActivity.this));

                    } else {
                        if (s.toString().trim().length()==0) {
                            //txtNoItem.setVisibility(View.GONE);
                            Log.v("s is 0","s is 0");
                            if (studentsList.size() != 0) {
                                Log.v("studentsList not 0", "studentsList not 0");
                                recyclerView.setVisibility(View.VISIBLE);
                                adapter = new SearchStudentListAdapter(studentsList, SearchStudentsListActivity.this);
                                recyclerView.setAdapter(adapter);
                                recyclerView.setItemAnimator(new DefaultItemAnimator());
                                recyclerView.setLayoutManager(new LinearLayoutManager(SearchStudentsListActivity.this));
                               // txtNoItem.setVisibility(View.GONE);
                            } else {
                                Log.v("studentsList not 0 else","studentsList not 0 else");
                                //txtNoItem.setVisibility(View.VISIBLE);
                            }
                        } else {
                            recyclerView.setVisibility(View.GONE);
                            Log.v("s is 0 else", "s is 0 else");
                            //txtNoItem.setVisibility(View.VISIBLE);
                        }
                    }
                    //  podListAdapter.getFilter().filter(s);
                }

                @Override
                public void beforeTextChanged(CharSequence s, int start, int count,
                                              int after) {

                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            };
            edStudentFilter.addTextChangedListener(mSearchTw);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
       // getMenuInflater().inflate(R.menu.menu_search_students_list, menu);
        return true;
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
