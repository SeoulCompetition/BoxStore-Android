package com.b05studio.boxstore.model;

/**
 * Created by joyeongje on 2017. 10. 31..
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Keyword {

    @SerializedName("uid")
    @Expose
    private String uid;
    @SerializedName("userToken")
    @Expose
    private String userToken;
    @SerializedName("keyword")
    @Expose
    private String keyword;

    /**
     * No args constructor for use in serialization
     *
     */
    public Keyword() {
    }

    /**
     *
     * @param uid
     * @param userToken
     * @param keyword
     */
    public Keyword(String uid, String userToken, String keyword) {
        super();
        this.uid = uid;
        this.userToken = userToken;
        this.keyword = keyword;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

}