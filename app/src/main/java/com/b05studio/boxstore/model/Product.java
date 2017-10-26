package com.b05studio.boxstore.model;

/**
 * Created by young on 2017-10-27.
 */

public class Product {

    private String sellerId;
    private String stuffName;
    private String category;
    private String productState;
    private String postType;
    private String stuffInfo;
    private String price;
    private String stationName;
    private String productionId;

    public Product(String sellerId, String stuffName, String category, String productState, String postType, String stuffInfo, String price, String stationName,String productionId) {
        this.sellerId = sellerId;
        this.stuffName = stuffName;
        this.category = category;
        this.productState = productState;
        this.postType = postType;
        this.stuffInfo = stuffInfo;
        this.price = price;
        this.stationName = stationName;
        this.productionId = productionId;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getStuffName() {
        return stuffName;
    }

    public void setStuffName(String stuffName) {
        this.stuffName = stuffName;
    }

    public String getStuffInfo() {
        return stuffInfo;
    }

    public void setStuffInfo(String stuffInfo) {
        this.stuffInfo = stuffInfo;
    }

    public String getProductState() {
        return productState;
    }

    public void setProductState(String productState) {
        this.productState = productState;
    }

    public String getPostType() {
        return postType;
    }

    public void setPostType(String postType) {
        this.postType = postType;
    }


    public String getProductionId() {
        return productionId;
    }

    public void setProductionId(String productionId) {
        this.productionId = productionId;
    }
}
