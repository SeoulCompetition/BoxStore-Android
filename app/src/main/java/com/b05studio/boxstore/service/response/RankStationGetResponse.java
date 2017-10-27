package com.b05studio.boxstore.service.response;

/**
 * Created by young on 2017-10-28.
 */

import java.util.List;

import com.b05studio.boxstore.model.Station;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RankStationGetResponse {

    @SerializedName("result")
    @Expose
    private String result;
    @SerializedName("stations")
    @Expose
    private List<Station> stations = null;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public List<Station> getStations() {
        return stations;
    }

    public void setStations(List<Station> stations) {
        this.stations = stations;
    }

}