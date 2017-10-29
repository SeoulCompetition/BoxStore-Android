
package com.b05studio.boxstore.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StationId {

    @SerializedName("stationName")
    @Expose
    private String stationName;

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

}
