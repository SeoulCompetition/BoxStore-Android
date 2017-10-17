package com.b05studio.boxstore.service.response;

/**
 * Created by joyeongje on 2017. 10. 16..
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BoxtorePostResponse {

    @SerializedName("RESULT")
    @Expose
    private String rESULT;
    @SerializedName("message")
    @Expose
    private String message;

    /**
     * No args constructor for use in serialization
     *
     */
    public BoxtorePostResponse() {
    }

    /**
     *
     * @param message
     * @param rESULT
     */
    public BoxtorePostResponse(String rESULT, String message) {
        super();
        this.rESULT = rESULT;
        this.message = message;
    }

    public String getRESULT() {
        return rESULT;
    }

    public void setRESULT(String rESULT) {
        this.rESULT = rESULT;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}