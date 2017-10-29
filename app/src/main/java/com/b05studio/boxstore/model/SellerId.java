
package com.b05studio.boxstore.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

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
