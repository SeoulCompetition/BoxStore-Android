
package com.b05studio.boxstore.service.response;

import com.b05studio.boxstore.model.Stuff;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class StuffGetResponse {

    @SerializedName("result")
    @Expose
    private String result;
    @SerializedName("stuffs")
    @Expose
    private List<Stuff> stuffs = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public StuffGetResponse() {
    }

    /**
     * 
     * @param result
     * @param stuffs
     */
    public StuffGetResponse(String result, List<Stuff> stuffs) {
        super();
        this.result = result;
        this.stuffs = stuffs;
    }

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
