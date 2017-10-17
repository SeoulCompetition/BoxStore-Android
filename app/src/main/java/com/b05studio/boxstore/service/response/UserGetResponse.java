package com.b05studio.boxstore.service.response;

/**
 * Created by young on 2017-10-06.
 */


import com.b05studio.boxstore.model.BoxstoreUser;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserGetResponse {

    @SerializedName("RESULT")
    @Expose
    private String rESULT;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("user_info")
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
     * @param userInfo
     * @param rESULT
     */
    public UserGetResponse(String rESULT, String message, BoxstoreUser userInfo) {
        super();
        this.rESULT = rESULT;
        this.message = message;
        this.userInfo = userInfo;
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

    public BoxstoreUser getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(BoxstoreUser userInfo) {
        this.userInfo = userInfo;
    }

}