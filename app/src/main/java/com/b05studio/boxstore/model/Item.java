package com.b05studio.boxstore.model;

/**
 * Created by seungwoo on 2017-09-27.
 */

public class Item {

    private String userProfileURL;
    private String userName;

    private String itemURL;
    private String itemTitle;
    private String itemPrice;

    public Item(String userProfileURL, String userName, String itemURL, String itemTitle, String itemPrice){
        this.userProfileURL = userProfileURL;
        this.userName = userName;
        this.itemURL = itemURL;
        this.itemPrice = itemPrice;
        this.itemTitle = itemTitle;
    }

    public Item(String itemURL,String itemTitle,String itemPrice) {
        this.itemURL = itemURL;
        this.itemPrice = itemPrice;
        this.itemTitle = itemTitle;
    }

    public String getUserProfileURL() {
        return userProfileURL;
    }

    public void setUserProfileURL(String userProfileURL) {
        this.userProfileURL = userProfileURL;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getItemURL() {
        return itemURL;
    }

    public void setItemURL(String itemURL) {
        this.itemURL = itemURL;
    }

    public String getItemTitle() {
        return itemTitle;
    }

    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(String itemPrice) {
        this.itemPrice = itemPrice;
    }
}
