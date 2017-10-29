
package com.b05studio.boxstore.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StationId {

    @SerializedName("stationName")
    @Expose
    private String stationName;

    /**
     * No args constructor for use in serialization
     * 
     */
    public StationId() {
    }

    /**
     * 
     * @param stationName
     */
    public StationId(String stationName) {
        super();
        this.stationName = stationName;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

}
