package com.b05studio.boxstore.service.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by joyeongje on 2017. 10. 31..
 */

public class MapGetResponse {

    @SerializedName("result")
    @Expose
    private String result;
    @SerializedName("mapURL")
    @Expose
    private String mapURL;

    public MapGetResponse(String result, String mapURL) {
        this.result = result;
        this.mapURL = mapURL;
    }

    public String getMapURL() {
        return mapURL;
    }

    public void setMapURL(String mapURL) {
        this.mapURL = mapURL;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}

