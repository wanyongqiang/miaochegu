package com.miaochegu.model;

import java.io.Serializable;

/**
 * Created by roztop on 2017/7/24.
 */

public class CarInfoModel implements Serializable {
    private int task_id;
    private String carType;
    private String time;
    private int audit_id;
    private int car_id;
    private String reason;//未过原因

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getTask_id() {
        return task_id;
    }

    public void setTask_id(int task_id) {
        this.task_id = task_id;
    }

    public CarInfoModel() {

    }

    public int getAudit_id() {
        return audit_id;
    }

    public void setAudit_id(int audit_id) {
        this.audit_id = audit_id;
    }

    public int getCar_id() {
        return car_id;
    }

    public void setCar_id(int car_id) {
        this.car_id = car_id;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public CarInfoModel(int task_id, String carType, String time) {
        this.task_id = task_id;
        this.carType = carType;
        this.time = time;
    }

    public CarInfoModel(int task_id, String carType, String time, int audit_id, int car_id) {
        this.task_id = task_id;
        this.carType = carType;
        this.time = time;
        this.audit_id = audit_id;
        this.car_id = car_id;
    }
}
