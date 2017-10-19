package com.b05studio.boxstore.service.response;

/**
 * Created by young on 2017-10-06.
 */


import com.b05studio.boxstore.model.BoxstoreUser;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserGetResponse {

    @SerializedName("result")
    @Expose
    private String result;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("userInfo")
    @Expose
    private BoxstoreUser userInfo;

    /**
     * No args constructor for use in serialization
     *
     */
    public UserGetResponse() {
    }

    /**
     *
     * @param message
     * @param result
     * @param userInfo
     */
    public UserGetResponse(String result, String message, BoxstoreUser userInfo) {
        super();
        this.result = result;
        this.message = message;
        this.userInfo = userInfo;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public BoxstoreUser getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(BoxstoreUser userInfo) {
        this.userInfo = userInfo;
    }

}