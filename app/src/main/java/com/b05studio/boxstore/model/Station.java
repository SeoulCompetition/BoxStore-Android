package com.b05studio.boxstore.model;

import java.util.List;

/**
 * Created by joyeongje on 2017. 10. 19..
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Station {

    @SerializedName("stationName")
    @Expose
    private String stationName;
    @SerializedName("stuffCount")
    @Expose
    private Integer stuffCount;

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public Integer getStuffCount() {
        return stuffCount;
    }

    public void setStuffCount(Integer stuffCount) {
        this.stuffCount = stuffCount;
    }

}
