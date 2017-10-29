
package com.b05studio.boxstore.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SellerId {

    @SerializedName("uid")
    @Expose
    private String uid;
    @SerializedName("keywords")
    @Expose
    private List<Object> keywords = null;
    @SerializedName("photoURL")
    @Expose
    private String photoURL;
    @SerializedName("userToken")
    @Expose
    private String userToken;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("join_date")
    @Expose
    private String joinDate;
    @SerializedName("phoneNum")
    @Expose
    private String phoneNum;
    @SerializedName("name")
    @Expose
    private String name;

    /**
     * No args constructor for use in serialization
     * 
     */
    public SellerId() {
    }

    /**
     * 
     * @param joinDate
     * @param uid
     * @param phoneNum
     * @param keywords
     * @param photoURL
     * @param email
     * @param userToken
     * @param name
     */
    public SellerId(String uid, List<Object> keywords, String photoURL, String userToken, String email, String joinDate, String phoneNum, String name) {
        super();
        this.uid = uid;
        this.keywords = keywords;
        this.photoURL = photoURL;
        this.userToken = userToken;
        this.email = email;
        this.joinDate = joinDate;
        this.phoneNum = phoneNum;
        this.name = name;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public List<Object> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<Object> keywords) {
        this.keywords = keywords;
    }

    public String getPhotoURL() {
        return photoURL;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(String joinDate) {
        this.joinDate = joinDate;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
