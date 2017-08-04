package com.miaochegu.model;

import java.io.Serializable;

/**
 * Created by roztop on 2017/7/24.
 */

public class CountryModel implements Serializable {

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    private int _id;
    private String carName;
    private String carType;
    private String carInfo;

    public CountryModel(String carName, String carType, String carInfo) {
        this.carName = carName;
        this.carType = carType;
        this.carInfo = carInfo;
    }

    public CountryModel() {
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getCarInfo() {
        return carInfo;
    }

    public void setCarInfo(String carInfo) {
        this.carInfo = carInfo;
    }

    @Override
    public String toString() {

        return carName;
    }
}
