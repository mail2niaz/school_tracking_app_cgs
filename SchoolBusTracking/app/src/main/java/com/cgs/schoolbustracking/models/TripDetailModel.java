package com.cgs.schoolbustracking.models;

/**
 * Created by ramya on 9/15/2015.
 */
public class TripDetailModel {
//    "vehice_id": "1",
//            "vehicle_num": "TN234A",
//            "route_id": "12",
//            "from": "saidapet",
//            "to": "school",
//            "planned_start_time": "6.30am",
//            "planned_end_time": "8.30am",
//            "planned_distance": "30km",
//

    String vehicleId;
    String vehicleNumber;
    String routeId;
    String fromArea;
    String toArea;
    String plannedStartTime;
    String plannedEndTime;
    String plannedDistance;

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }

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

    public String getPlannedDistance() {
        return plannedDistance;
    }

    public void setPlannedDistance(String plannedDistance) {
        this.plannedDistance = plannedDistance;
    }
}
