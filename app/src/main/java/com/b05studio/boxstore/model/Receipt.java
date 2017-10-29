
package com.b05studio.boxstore.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Receipt {

    @SerializedName("lockerInfo")
    @Expose
    private String lockerInfo;
    @SerializedName("lockerPw")
    @Expose
    private String lockerPw;
    @SerializedName("lockerNum")
    @Expose
    private String lockerNum;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Receipt() {
    }

    /**
     * 
     * @param lockerInfo
     * @param lockerPw
     * @param lockerNum
     */
    public Receipt(String lockerInfo, String lockerPw, String lockerNum) {
        super();
        this.lockerInfo = lockerInfo;
        this.lockerPw = lockerPw;
        this.lockerNum = lockerNum;
    }

    public String getLockerInfo() {
        return lockerInfo;
    }

    public void setLockerInfo(String lockerInfo) {
        this.lockerInfo = lockerInfo;
    }

    public String getLockerPw() {
        return lockerPw;
    }

    public void setLockerPw(String lockerPw) {
        this.lockerPw = lockerPw;
    }

    public String getLockerNum() {
        return lockerNum;
    }

    public void setLockerNum(String lockerNum) {
        this.lockerNum = lockerNum;
    }

}
