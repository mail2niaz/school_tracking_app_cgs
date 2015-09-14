package com.cgs.schoolbustracking.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ramya on 08/09/2015.
 */
public class BusStopNameModel implements Serializable {

    String busStopName;
    String busStopCount;
    List<StudentDetailModel> studentDetailModelArrayList;

    public String getBusStopCount() {
        return busStopCount;
    }

    public void setBusStopCount(String busStopCount) {
        this.busStopCount = busStopCount;
    }

    public String getBusStopName() {
        return busStopName;
    }

    public void setBusStopName(String busStopName) {
        this.busStopName = busStopName;
    }

    public List<StudentDetailModel> getStudentDetailModelArrayList() {
        return studentDetailModelArrayList;
    }

    public void setStudentDetailModelArrayList(List<StudentDetailModel> studentDetailModelArrayList) {
        this.studentDetailModelArrayList = studentDetailModelArrayList;
    }
}
