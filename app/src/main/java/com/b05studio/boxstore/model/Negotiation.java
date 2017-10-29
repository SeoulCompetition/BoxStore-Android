
package com.b05studio.boxstore.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Negotiation {

    @SerializedName("stationId")
    @Expose
    private String stationId;
    @SerializedName("done")
    @Expose
    private String done;
    @SerializedName("price")
    @Expose
    private Integer price;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Negotiation() {
    }

    /**
     * 
     * @param price
     * @param done
     * @param stationId
     */
    public Negotiation(String stationId, String done, Integer price) {
        super();
        this.stationId = stationId;
        this.done = done;
        this.price = price;
    }

    public String getStationId() {
        return stationId;
    }

    public void setStationId(String stationId) {
        this.stationId = stationId;
    }

    public String getDone() {
        return done;
    }

    public void setDone(String done) {
        this.done = done;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

}
