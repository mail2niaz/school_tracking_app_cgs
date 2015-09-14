package com.cgs.schoolbustracking.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.cgs.schoolbustracking.models.BusStopNameModel;
import com.cgs.schoolbustracking.models.DriverModel;
import com.cgs.schoolbustracking.models.StudentDetailModel;
import com.cgs.schoolbustracking.models.VehicleModel;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by ramya on 23/07/2015.
 */
public class DatabaseHandler extends SQLiteOpenHelper {


    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "SchoolBusTrackingDB";


    //-----------------------------------Table students info-----------------------------------------------

    // Contacts table name
    private static final String TABLE_STUDENT_DETAILS = "student_details";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_CLASS = "class";
    private static final String KEY_SECTION = "section";
    private static final String KEY_GENDER = "gender";
    private static final String KEY_DOB = "dob";
    private static final String KEY_BLOOD_GROUP = "blood_group";
    private static final String KEY_PARENT_NAME = "parent_name";
    private static final String KEY_PARENT_NUMBER = "parent_number";
    private static final String KEY_FULL_ADDRESS = "full_address";
    private static final String KEY_BUSSTOP_NAME = "busstop_name";
    private static final String KEY_STUDENT_CHECKIN = "student_checkin";
    private static final String KEY_STUD_DRIVER_ID = "student_driver_id";
    private static final String KEY_STUD_VEHICLE_ID= "student_vehicle_id";

  //-----------------------------------Table busStop info-----------------------------------------------

    // Contacts table name
    private static final String TABLE_BUS_DETAILS = "bus_details";

    // Contacts Table Columns names
    private static final String KEY_BUSSTOP_ID = "busstop_id";
    private static final String KEY_BUSTOP_NAME = "busstop_name";
   private static final String KEY_STUDENT_SIZE = "student_count";


    //-----------------------------------Table Driver info-----------------------------------------------

    // Driver table name
    private static final String TABLE_DRIVER_DETAILS = "driver_details";

    // Driver Table Columns names
    private static final String KEY_DRIVER_ID = "driver_id";
    private static final String KEY_DRIVER_NAME = "driver_name";
    private static final String KEY_DRIVER_NUMBER = "driver_number";

    //-----------------------------------Table Vehicle info-----------------------------------------------

    // Driver table name
    private static final String TABLE_VEHICLE_DETAILS = "vehicle_details";

    // Driver Table Columns names
    private static final String KEY_VEHICLE_ID = "vehicle_id";
    private static final String KEY_VEHICLE_NUMBER = "vehicle_number";





    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_STUDENT_TABLE = "CREATE TABLE " + TABLE_STUDENT_DETAILS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"+ KEY_CLASS + " TEXT,"
                + KEY_SECTION + " TEXT,"+KEY_GENDER + " TEXT,"+ KEY_DOB + " TEXT,"+ KEY_STUDENT_CHECKIN + " TEXT,"
                +KEY_BLOOD_GROUP + " TEXT,"+KEY_PARENT_NAME + " TEXT,"+KEY_PARENT_NUMBER + " TEXT,"
                +KEY_FULL_ADDRESS+ " TEXT,"+KEY_STUD_DRIVER_ID + " TEXT,"+KEY_STUD_VEHICLE_ID + " TEXT,"+KEY_BUSSTOP_NAME+ " TEXT"+ ")";

        String CREATE_BUSSTOP_TABLE = "CREATE TABLE " + TABLE_BUS_DETAILS + "("
                + KEY_BUSSTOP_ID + " INTEGER PRIMARY KEY," +KEY_BUSTOP_NAME+ " TEXT,"+KEY_STUDENT_SIZE+ " TEXT"+ ")";

        String CREATE_DRIVER_TABLE = "CREATE TABLE " + TABLE_DRIVER_DETAILS + "("
                + KEY_DRIVER_ID + " TEXT," +KEY_DRIVER_NAME+ " TEXT,"+KEY_DRIVER_NUMBER+ " TEXT"+ ")";

        String CREATE_VEHICLE_TABLE = "CREATE TABLE " + TABLE_VEHICLE_DETAILS + "("
                + KEY_VEHICLE_ID + " TEXT," + KEY_VEHICLE_NUMBER+ " TEXT"+ ")";

        db.execSQL(CREATE_STUDENT_TABLE);
        db.execSQL(CREATE_BUSSTOP_TABLE);
        db.execSQL(CREATE_DRIVER_TABLE);
        db.execSQL(CREATE_VEHICLE_TABLE);

    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENT_DETAILS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BUS_DETAILS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DRIVER_DETAILS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_VEHICLE_DETAILS);
        // Create tables again
        onCreate(db);
    }


   //***************************************CRUD OPERATIONS START***********************************

    /**
     * to insert a single student detail
     * @param studentDetailModel
     */
    public void insertStudent(StudentDetailModel studentDetailModel) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, studentDetailModel.getName());
        values.put(KEY_CLASS, studentDetailModel.getClasss());
        values.put(KEY_SECTION, studentDetailModel.getSection());
        values.put(KEY_DOB, studentDetailModel.getDob());
        values.put(KEY_GENDER, studentDetailModel.getGender());
        values.put(KEY_BLOOD_GROUP, studentDetailModel.getBloodGroup());
        values.put(KEY_PARENT_NAME, studentDetailModel.getParentName());
        values.put(KEY_PARENT_NUMBER, studentDetailModel.getParentNumber());
        values.put(KEY_FULL_ADDRESS, studentDetailModel.getFullAddress());
        values.put(KEY_BUSSTOP_NAME, studentDetailModel.getBusStopName());
        values.put(KEY_STUD_DRIVER_ID, studentDetailModel.getDriverId());
        values.put(KEY_STUD_VEHICLE_ID, studentDetailModel.getVehicleId());
        values.put(KEY_STUDENT_CHECKIN, String.valueOf(studentDetailModel.isStudentCheckIn()));

        // Inserting Row
        db.insert(TABLE_STUDENT_DETAILS, null, values);
        // Closing database connection
        db.close();
    }



    /**
     * to update a single student detail
     * @param studentDetailModel
     */
    public void updateStudent(StudentDetailModel studentDetailModel) {

        //System.out.println("postion" + postion);
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, studentDetailModel.getName());
        values.put(KEY_CLASS, studentDetailModel.getClasss());
        values.put(KEY_SECTION, studentDetailModel.getSection());
        values.put(KEY_DOB, studentDetailModel.getDob());
        values.put(KEY_GENDER, studentDetailModel.getGender());
        values.put(KEY_BLOOD_GROUP, studentDetailModel.getBloodGroup());
        values.put(KEY_PARENT_NAME, studentDetailModel.getParentName());
        values.put(KEY_PARENT_NUMBER, studentDetailModel.getParentNumber());
        values.put(KEY_FULL_ADDRESS, studentDetailModel.getFullAddress());
        values.put(KEY_BUSSTOP_NAME, studentDetailModel.getBusStopName());
        values.put(KEY_STUD_DRIVER_ID, studentDetailModel.getDriverId());
        values.put(KEY_STUD_VEHICLE_ID, studentDetailModel.getVehicleId());
        if(studentDetailModel.isStudentCheckIn()) {
            values.put(KEY_STUDENT_CHECKIN, String.valueOf(false));
        }else{
            values.put(KEY_STUDENT_CHECKIN, String.valueOf(true));
        }


        // updating row
        int v=  db.update(TABLE_STUDENT_DETAILS, values, KEY_ID + " = ?",
                 new String[]{String.valueOf(studentDetailModel.getId())});
Log.v("üpdatepos", "üpdatepos" + String.valueOf(studentDetailModel.getId()));

        // Closing database connection
        db.close();
    }

    // Getting All Contacts
    public List<StudentDetailModel> getStudentsList() {
        List<StudentDetailModel> studentsList = new ArrayList<StudentDetailModel>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_STUDENT_DETAILS;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);

//        Cursor cursor = db.query(TABLE_CONTACTS, new String[]{KEY_ID,
//                        KEY_FIRSTNAME, KEY_lASTNAME, KEY_MIDDLENAME, KEY_PH_NO, KEY_MAILID, KEY_ADDRESS_LINE_ONE,
//                        KEY_ADDRESS_LINE_TWO, KEY_ADDRESS_LINE_THREE, KEY_CITY, KEY_STATE, KEY_COUNTRY, KEY_PINCODE,
//                        KEY_REFERENCENO, KEY_ACTIVE, KEY_REMARKS, KEY_ROLE, KEY_CLIENTPHOTO, KEY_STATUS,KEY_SYNC,KEY_FILEPATH,KEY_PICKUPCODE}, KEY_ROLE + "=? AND " + KEY_EMPID + "=?",
//                new String[]{String.valueOf(role), empId}, null, null, null, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                StudentDetailModel item = new StudentDetailModel();
                item.setName(cursor.getString(cursor.getColumnIndex(KEY_NAME)));
                item.setClasss(cursor.getString(cursor.getColumnIndex(KEY_CLASS)));
                item.setSection(cursor.getString(cursor.getColumnIndex(KEY_SECTION)));
                item.setDob(cursor.getString(cursor.getColumnIndex(KEY_DOB)));
                item.setGender(cursor.getString(cursor.getColumnIndex(KEY_GENDER)));
                item.setBloodGroup(cursor.getString(cursor.getColumnIndex(KEY_BLOOD_GROUP)));
                item.setParentName(cursor.getString(cursor.getColumnIndex(KEY_PARENT_NAME)));
                item.setParentNumber(cursor.getString(cursor.getColumnIndex(KEY_PARENT_NUMBER)));
                item.setFullAddress(cursor.getString(cursor.getColumnIndex(KEY_FULL_ADDRESS)));
                item.setBusStopName(cursor.getString(cursor.getColumnIndex(KEY_BUSSTOP_NAME)));
                item.setStudentCheckIn(Boolean.parseBoolean(cursor.getString(cursor.getColumnIndex(KEY_STUDENT_CHECKIN))));
                item.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(KEY_ID))));
                item.setDriverId(cursor.getString(cursor.getColumnIndex(KEY_STUD_DRIVER_ID)));
                item.setVehicleId(cursor.getString(cursor.getColumnIndex(KEY_STUD_VEHICLE_ID)));
                // Adding contact to list
                studentsList.add(item);
            } while (cursor.moveToNext());
        }
        db.close();
        Log.v("db studentsList", "studentsList" + studentsList.size());
        // return contact list
        return studentsList;
    }
    // Getting All Contacts
    public List<StudentDetailModel> getStudentsListWhereTrue() {
        List<StudentDetailModel> studentsList = new ArrayList<StudentDetailModel>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_STUDENT_DETAILS +" WHERE "+KEY_STUDENT_CHECKIN +"= true";

        SQLiteDatabase db = this.getReadableDatabase();
       // Cursor cursor = db.rawQuery(selectQuery, null);
        Cursor cursor = db.rawQuery(
                "SELECT  * FROM " + TABLE_STUDENT_DETAILS +" WHERE "+KEY_STUDENT_CHECKIN +"=?", new String[] { "true" });

//        Cursor cursor = db.query(TABLE_CONTACTS, new String[]{KEY_ID,
//                        KEY_FIRSTNAME, KEY_lASTNAME, KEY_MIDDLENAME, KEY_PH_NO, KEY_MAILID, KEY_ADDRESS_LINE_ONE,
//                        KEY_ADDRESS_LINE_TWO, KEY_ADDRESS_LINE_THREE, KEY_CITY, KEY_STATE, KEY_COUNTRY, KEY_PINCODE,
//                        KEY_REFERENCENO, KEY_ACTIVE, KEY_REMARKS, KEY_ROLE, KEY_CLIENTPHOTO, KEY_STATUS,KEY_SYNC,KEY_FILEPATH,KEY_PICKUPCODE}, KEY_ROLE + "=? AND " + KEY_EMPID + "=?",
//                new String[]{String.valueOf(role), empId}, null, null, null, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                StudentDetailModel item = new StudentDetailModel();
                item.setName(cursor.getString(cursor.getColumnIndex(KEY_NAME)));
                item.setClasss(cursor.getString(cursor.getColumnIndex(KEY_CLASS)));
                item.setSection(cursor.getString(cursor.getColumnIndex(KEY_SECTION)));
                item.setDob(cursor.getString(cursor.getColumnIndex(KEY_DOB)));
                item.setGender(cursor.getString(cursor.getColumnIndex(KEY_GENDER)));
                item.setBloodGroup(cursor.getString(cursor.getColumnIndex(KEY_BLOOD_GROUP)));
                item.setParentName(cursor.getString(cursor.getColumnIndex(KEY_PARENT_NAME)));
                item.setParentNumber(cursor.getString(cursor.getColumnIndex(KEY_PARENT_NUMBER)));
                item.setFullAddress(cursor.getString(cursor.getColumnIndex(KEY_FULL_ADDRESS)));
                item.setBusStopName(cursor.getString(cursor.getColumnIndex(KEY_BUSSTOP_NAME)));
                item.setStudentCheckIn(Boolean.parseBoolean(cursor.getString(cursor.getColumnIndex(KEY_STUDENT_CHECKIN))));
                item.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(KEY_ID))));
                item.setDriverId(cursor.getString(cursor.getColumnIndex(KEY_STUD_DRIVER_ID)));
                item.setVehicleId(cursor.getString(cursor.getColumnIndex(KEY_STUD_VEHICLE_ID)));
                // Adding contact to list
                studentsList.add(item);
            } while (cursor.moveToNext());
        }

        // return contact list


        return studentsList;
    }


    public List<StudentDetailModel> getStudentsListOfParticularBusStop(String busStopName) {
        List<StudentDetailModel> studentsList = new ArrayList<StudentDetailModel>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_STUDENT_DETAILS +" WHERE "+KEY_STUDENT_CHECKIN +"= true";

        SQLiteDatabase db = this.getReadableDatabase();
        // Cursor cursor = db.rawQuery(selectQuery, null);
        Cursor cursor = db.rawQuery(
                "SELECT  * FROM " + TABLE_STUDENT_DETAILS +" WHERE "+KEY_BUSSTOP_NAME +" = ?", new String[] { busStopName });

//        Cursor cursor = db.query(TABLE_CONTACTS, new String[]{KEY_ID,
//                        KEY_FIRSTNAME, KEY_lASTNAME, KEY_MIDDLENAME, KEY_PH_NO, KEY_MAILID, KEY_ADDRESS_LINE_ONE,
//                        KEY_ADDRESS_LINE_TWO, KEY_ADDRESS_LINE_THREE, KEY_CITY, KEY_STATE, KEY_COUNTRY, KEY_PINCODE,
//                        KEY_REFERENCENO, KEY_ACTIVE, KEY_REMARKS, KEY_ROLE, KEY_CLIENTPHOTO, KEY_STATUS,KEY_SYNC,KEY_FILEPATH,KEY_PICKUPCODE}, KEY_ROLE + "=? AND " + KEY_EMPID + "=?",
//                new String[]{String.valueOf(role), empId}, null, null, null, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                StudentDetailModel item = new StudentDetailModel();
                item.setName(cursor.getString(cursor.getColumnIndex(KEY_NAME)));
                item.setClasss(cursor.getString(cursor.getColumnIndex(KEY_CLASS)));
                item.setSection(cursor.getString(cursor.getColumnIndex(KEY_SECTION)));
                item.setDob(cursor.getString(cursor.getColumnIndex(KEY_DOB)));
                item.setGender(cursor.getString(cursor.getColumnIndex(KEY_GENDER)));
                item.setBloodGroup(cursor.getString(cursor.getColumnIndex(KEY_BLOOD_GROUP)));
                item.setParentName(cursor.getString(cursor.getColumnIndex(KEY_PARENT_NAME)));
                item.setParentNumber(cursor.getString(cursor.getColumnIndex(KEY_PARENT_NUMBER)));
                item.setFullAddress(cursor.getString(cursor.getColumnIndex(KEY_FULL_ADDRESS)));
                item.setBusStopName(cursor.getString(cursor.getColumnIndex(KEY_BUSSTOP_NAME)));
                item.setStudentCheckIn(Boolean.parseBoolean(cursor.getString(cursor.getColumnIndex(KEY_STUDENT_CHECKIN))));
                item.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(KEY_ID))));
                item.setDriverId(cursor.getString(cursor.getColumnIndex(KEY_STUD_DRIVER_ID)));
                item.setVehicleId(cursor.getString(cursor.getColumnIndex(KEY_STUD_VEHICLE_ID)));
                // Adding contact to list
                studentsList.add(item);
            } while (cursor.moveToNext());
        }

        // return contact list


        return studentsList;
    }



    public List<StudentDetailModel> getArrayStudentsListOfParticularBusStop(String busStopName,ArrayList<String> listbusName) {


        ArrayList<String> listbusname_ar=new ArrayList<String>();

        listbusname_ar.add(busStopName);
        for(int i=0;i<listbusName.size();i++){
            if(!listbusname_ar.contains(listbusName.get(i))) {
                listbusname_ar.add(listbusName.get(i));
            }
        }
        List<StudentDetailModel> studentsList = new ArrayList<StudentDetailModel>();

        for(int i=0;i<listbusname_ar.size();i++) {

            Cursor cursor=null;
            // Select All Query
            String selectQuery = "SELECT  * FROM " + TABLE_STUDENT_DETAILS + " WHERE " + KEY_STUDENT_CHECKIN + "= true";

            SQLiteDatabase db = this.getReadableDatabase();
            // Cursor cursor = db.rawQuery(selectQuery, null);

            if(i==0) {
                cursor = db.rawQuery(
                        "SELECT  * FROM " + TABLE_STUDENT_DETAILS + " WHERE " + KEY_BUSSTOP_NAME + " = ?", new String[]{busStopName});
            }else{
                cursor = db.rawQuery(
                        "SELECT  * FROM " + TABLE_STUDENT_DETAILS + " WHERE "+KEY_STUDENT_CHECKIN +"=? AND " + KEY_BUSSTOP_NAME + " = ?", new String[]{"false",listbusname_ar.get(i)});
            }

//        Cursor cursor = db.query(TABLE_CONTACTS, new String[]{KEY_ID,
//                        KEY_FIRSTNAME, KEY_lASTNAME, KEY_MIDDLENAME, KEY_PH_NO, KEY_MAILID, KEY_ADDRESS_LINE_ONE,
//                        KEY_ADDRESS_LINE_TWO, KEY_ADDRESS_LINE_THREE, KEY_CITY, KEY_STATE, KEY_COUNTRY, KEY_PINCODE,
//                        KEY_REFERENCENO, KEY_ACTIVE, KEY_REMARKS, KEY_ROLE, KEY_CLIENTPHOTO, KEY_STATUS,KEY_SYNC,KEY_FILEPATH,KEY_PICKUPCODE}, KEY_ROLE + "=? AND " + KEY_EMPID + "=?",
//                new String[]{String.valueOf(role), empId}, null, null, null, null);

            // looping through all rows and adding to list
            if (cursor.moveToFirst()) {
                do {
                    StudentDetailModel item = new StudentDetailModel();
                    item.setName(cursor.getString(cursor.getColumnIndex(KEY_NAME)));
                    item.setClasss(cursor.getString(cursor.getColumnIndex(KEY_CLASS)));
                    item.setSection(cursor.getString(cursor.getColumnIndex(KEY_SECTION)));
                    item.setDob(cursor.getString(cursor.getColumnIndex(KEY_DOB)));
                    item.setGender(cursor.getString(cursor.getColumnIndex(KEY_GENDER)));
                    item.setBloodGroup(cursor.getString(cursor.getColumnIndex(KEY_BLOOD_GROUP)));
                    item.setParentName(cursor.getString(cursor.getColumnIndex(KEY_PARENT_NAME)));
                    item.setParentNumber(cursor.getString(cursor.getColumnIndex(KEY_PARENT_NUMBER)));
                    item.setFullAddress(cursor.getString(cursor.getColumnIndex(KEY_FULL_ADDRESS)));
                    item.setBusStopName(cursor.getString(cursor.getColumnIndex(KEY_BUSSTOP_NAME)));
                    item.setStudentCheckIn(Boolean.parseBoolean(cursor.getString(cursor.getColumnIndex(KEY_STUDENT_CHECKIN))));
                    item.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(KEY_ID))));
                    item.setDriverId(cursor.getString(cursor.getColumnIndex(KEY_STUD_DRIVER_ID)));
                    item.setVehicleId(cursor.getString(cursor.getColumnIndex(KEY_STUD_VEHICLE_ID)));
                    // Adding contact to list
                    studentsList.add(item);
                } while (cursor.moveToNext());
            }

            // return contact list
        }

        return studentsList;
    }
    // Getting All Contacts
    public List<StudentDetailModel> getStudentsListWhereTrueOnBusstop(String busstop) {
        List<StudentDetailModel> studentsList = new ArrayList<StudentDetailModel>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_STUDENT_DETAILS +" WHERE "+KEY_STUDENT_CHECKIN +"= true";

        SQLiteDatabase db = this.getReadableDatabase();
        // Cursor cursor = db.rawQuery(selectQuery, null);
        Cursor cursor = db.rawQuery(
                "SELECT  * FROM " + TABLE_STUDENT_DETAILS +" WHERE "+KEY_STUDENT_CHECKIN +"=? AND " + KEY_BUSSTOP_NAME + "=?", new String[] { "true", busstop});

//        Cursor cursor = db.query(TABLE_CONTACTS, new String[]{KEY_ID,
//                        KEY_FIRSTNAME, KEY_lASTNAME, KEY_MIDDLENAME, KEY_PH_NO, KEY_MAILID, KEY_ADDRESS_LINE_ONE,
//                        KEY_ADDRESS_LINE_TWO, KEY_ADDRESS_LINE_THREE, KEY_CITY, KEY_STATE, KEY_COUNTRY, KEY_PINCODE,
//                        KEY_REFERENCENO, KEY_ACTIVE, KEY_REMARKS, KEY_ROLE, KEY_CLIENTPHOTO, KEY_STATUS,KEY_SYNC,KEY_FILEPATH,KEY_PICKUPCODE}, KEY_ROLE + "=? AND " + KEY_EMPID + "=?",
//                new String[]{String.valueOf(role), empId}, null, null, null, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                StudentDetailModel item = new StudentDetailModel();
                item.setName(cursor.getString(cursor.getColumnIndex(KEY_NAME)));
                item.setClasss(cursor.getString(cursor.getColumnIndex(KEY_CLASS)));
                item.setSection(cursor.getString(cursor.getColumnIndex(KEY_SECTION)));
                item.setDob(cursor.getString(cursor.getColumnIndex(KEY_DOB)));
                item.setGender(cursor.getString(cursor.getColumnIndex(KEY_GENDER)));
                item.setBloodGroup(cursor.getString(cursor.getColumnIndex(KEY_BLOOD_GROUP)));
                item.setParentName(cursor.getString(cursor.getColumnIndex(KEY_PARENT_NAME)));
                item.setParentNumber(cursor.getString(cursor.getColumnIndex(KEY_PARENT_NUMBER)));
                item.setFullAddress(cursor.getString(cursor.getColumnIndex(KEY_FULL_ADDRESS)));
                item.setBusStopName(cursor.getString(cursor.getColumnIndex(KEY_BUSSTOP_NAME)));
                item.setStudentCheckIn(Boolean.parseBoolean(cursor.getString(cursor.getColumnIndex(KEY_STUDENT_CHECKIN))));
                item.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(KEY_ID))));
                item.setDriverId(cursor.getString(cursor.getColumnIndex(KEY_STUD_DRIVER_ID)));
                item.setVehicleId(cursor.getString(cursor.getColumnIndex(KEY_STUD_VEHICLE_ID)));
                // Adding contact to list
                studentsList.add(item);
            } while (cursor.moveToNext());
        }

        // return contact list


        return studentsList;
    }



    //*****************************************************

    /**
     * to insert a single student detail
     * @param //studentDetailModel
     */
    public void insertBusStop(BusStopNameModel busStopNameModel) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_BUSTOP_NAME, busStopNameModel.getBusStopName());
        values.put(KEY_STUDENT_SIZE, busStopNameModel.getBusStopCount());
        Log.v("inserted", "inserted" + busStopNameModel.getBusStopName() +busStopNameModel.getBusStopCount());
        // Inserting Row
        db.insert(TABLE_BUS_DETAILS, null, values);
        // Closing database connection
        db.close();


    }
    // Getting All Contacts
    public List<BusStopNameModel> getBusStopList() {



        List<BusStopNameModel> studentsList = new ArrayList<BusStopNameModel>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_BUS_DETAILS;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);


        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {

                Log.v("get", "getjjjjjjjjjjjjjjjjjj" );
                BusStopNameModel item = new BusStopNameModel();
                item.setBusStopName(cursor.getString(cursor.getColumnIndex(KEY_BUSTOP_NAME)));
             //Log.v("inserted", "inserted" + studentDetailModel.getBusStopName());
              item.setBusStopCount(String.valueOf(cursor.getString(cursor.getColumnIndex(KEY_STUDENT_SIZE))));

                // Adding contact to list
                studentsList.add(item);

            } while (cursor.moveToNext());
        }

        Log.v("db getBusStopList","busStopNameList---->"+studentsList.size());
        db.close();
        // return contact list
        return studentsList;
    }


    //**********************************CRUD FOR DRIVER TABLE - START**************************************
    /**
     * to insert a driver detail
     * @param driverModel
     */
    public void insertDriver(DriverModel driverModel) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_DRIVER_ID, driverModel.getDriverId());
        values.put(KEY_DRIVER_NAME, driverModel.getDriverName());
        values.put(KEY_DRIVER_NUMBER, driverModel.getDriverNumber());
        Log.v("inserted", "inserted" + driverModel.getDriverName() +driverModel.getDriverNumber());
        // Inserting Row
        db.insert(TABLE_DRIVER_DETAILS, null, values);
        // Closing database connection
        db.close();
    }

    // Getting All Contacts
    public DriverModel getDriver() {

            DriverModel driverModel = new DriverModel();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_DRIVER_DETAILS;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);


        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {

                Log.v("get", "getjjjjjjjjjjjjjjjjjj" );
                driverModel.setDriverId(cursor.getString(cursor.getColumnIndex(KEY_DRIVER_ID)));
                driverModel.setDriverName(String.valueOf(cursor.getString(cursor.getColumnIndex(KEY_DRIVER_NAME))));
                driverModel.setDriverNumber(String.valueOf(cursor.getString(cursor.getColumnIndex(KEY_DRIVER_NUMBER))));

            } while (cursor.moveToNext());
        }

        db.close();
        Log.v("get", "getdriver--->" + driverModel.getDriverName() + driverModel.getDriverNumber());
        // return contact list
        return driverModel;
    }


    //**********************************CRUD FOR DRIVER TABLE - END**************************************



    //**********************************CRUD FOR VEHICLE TABLE - START**************************************


    /**
     * to insert a driver detail
     * @param vehicleModel
     */
    public void insertVehicle(VehicleModel vehicleModel) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_VEHICLE_ID, vehicleModel.getVehicleId());
        values.put(KEY_VEHICLE_NUMBER, vehicleModel.getVehicleNumber());
        Log.v("inserted", "inserted" + vehicleModel.getVehicleId() + vehicleModel.getVehicleNumber());
        // Inserting Row
        db.insert(TABLE_VEHICLE_DETAILS, null, values);
        // Closing database connection
        db.close();
    }


    // Getting All Contacts
    public VehicleModel getVehicle() {

        VehicleModel vehicleModel = new VehicleModel();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_VEHICLE_DETAILS;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);


        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {


                vehicleModel.setVehicleId(cursor.getString(cursor.getColumnIndex(KEY_VEHICLE_ID)));
                vehicleModel.setVehicleNumber(String.valueOf(cursor.getString(cursor.getColumnIndex(KEY_VEHICLE_NUMBER))));

            } while (cursor.moveToNext());
        }

        db.close();
        Log.v("get", "getvehicle---=>" + vehicleModel.getVehicleId() + vehicleModel.getVehicleNumber());
        // return contact list
        return vehicleModel;
    }

}
