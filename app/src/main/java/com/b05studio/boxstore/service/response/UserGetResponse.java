package com.b05studio.boxstore.service.response;

/**
 * Created by young on 2017-10-06.
 */


import com.google.firebase.auth.UserInfo;
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
    private UserInfo userInfo;

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
    public UserGetResponse(String result, String message, UserInfo userInfo) {
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

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

}