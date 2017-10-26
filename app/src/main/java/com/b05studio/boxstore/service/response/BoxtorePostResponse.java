package com.b05studio.boxstore.service.response;

/**
 * Created by joyeongje on 2017. 10. 16..
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BoxtorePostResponse {

    @SerializedName("result")
    @Expose
    private String result;
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
     * @param result
     */
    public BoxtorePostResponse(String result, String message) {
        super();
        this.result = result;
        this.message = message;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}