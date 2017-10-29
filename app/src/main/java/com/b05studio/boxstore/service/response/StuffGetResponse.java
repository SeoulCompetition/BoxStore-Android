
package com.b05studio.boxstore.service.response;

import java.util.List;

import com.b05studio.boxstore.model.Stuff;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StuffGetResponse {

    @SerializedName("result")
    @Expose
    private String result;
    @SerializedName("stuffs")
    @Expose
    private List<Stuff> stuffs = null;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public List<Stuff> getStuffs() {
        return stuffs;
    }

    public void setStuffs(List<Stuff> stuffs) {
        this.stuffs = stuffs;
    }

}
