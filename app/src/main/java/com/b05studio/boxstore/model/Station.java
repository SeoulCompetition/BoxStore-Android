package com.b05studio.boxstore.model;

import java.util.List;

/**
 * Created by joyeongje on 2017. 10. 19..
 */

public class Station {

    private String stationName;
    private List<String> subStationList;

    public Station(String stationName, List<String> subStationList) {
        this.stationName = stationName;
        this.subStationList = subStationList;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public List<String> getSubStationList() {
        return subStationList;
    }

    public void setSubStationList(List<String> subStationList) {
        this.subStationList = subStationList;
    }
}
