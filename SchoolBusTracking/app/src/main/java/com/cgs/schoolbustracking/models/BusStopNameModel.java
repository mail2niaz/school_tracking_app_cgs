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

    //----
    String fromArea;
    String toArea;
    String plannedStartTime;
    String plannedEndTime;
    String vehicleId;


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

    //-----------------------------------------


    public String getFromArea() {
        return fromArea;
    }

    public void setFromArea(String fromArea) {
        this.fromArea = fromArea;
    }

    public String getToArea() {
        return toArea;
    }

    public void setToArea(String toArea) {
        this.toArea = toArea;
    }

    public String getPlannedStartTime() {
        return plannedStartTime;
    }

    public void setPlannedStartTime(String plannedStartTime) {
        this.plannedStartTime = plannedStartTime;
    }

    public String getPlannedEndTime() {
        return plannedEndTime;
    }

    public void setPlannedEndTime(String plannedEndTime) {
        this.plannedEndTime = plannedEndTime;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }
}
