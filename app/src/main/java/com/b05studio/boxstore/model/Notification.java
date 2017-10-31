package com.b05studio.boxstore.model;

/**
 * Created by seungwoo on 2017-10-31.
 */

public class Notification {
    private String device_token;
    private String from;
    private String Name;
    private String stuff_name;
    private String type;
    private String SellerUID;
    private String SellerName;

    public String getSellerUID() {
        return SellerUID;
    }

    public void setSellerUID(String sellerUID) {
        SellerUID = sellerUID;
    }

    public String getSellerName() {
        return SellerName;
    }

    public void setSellerName(String sellerName) {
        SellerName = sellerName;
    }

    public String getStuff_id() {
        return stuff_id;
    }

    public void setStuff_id(String stuff_id) {
        this.stuff_id = stuff_id;
    }

    public String getStuff_image() {
        return stuff_image;
    }

    public void setStuff_image(String stuff_image) {
        this.stuff_image = stuff_image;
    }

    public String getStuff_price() {
        return stuff_price;
    }

    public void setStuff_price(String stuff_price) {
        this.stuff_price = stuff_price;
    }

    private String stuff_id;
    private String stuff_image;
    private String stuff_price;

    public String getDevice_token() {
        return device_token;
    }

    public void setDevice_token(String device_token) {
        this.device_token = device_token;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getStuff_name() {
        return stuff_name;
    }

    public void setStuff_name(String stuff_name) {
        this.stuff_name = stuff_name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
