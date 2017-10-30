
package com.b05studio.boxstore.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Stuff implements Serializable {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("stationId")
    @Expose
    private StationId stationId;
    @SerializedName("sellerId")
    @Expose
    private SellerId sellerId;
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("postType")
    @Expose
    private String postType;
    @SerializedName("price")
    @Expose
    private Integer price;
    @SerializedName("stuffInfo")
    @Expose
    private String stuffInfo;
    @SerializedName("stuffName")
    @Expose
    private String stuffName;
    @SerializedName("transactionStatus")
    @Expose
    private String transactionStatus;
    @SerializedName("imageUrl")
    @Expose
    private List<String> imageUrl = null;
    @SerializedName("createdDate")
    @Expose
    private String createdDate;

    @SerializedName("productState")
    @Expose
    private String productState;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public StationId getStationId() {
        return stationId;
    }

    public void setStationId(StationId stationId) {
        this.stationId = stationId;
    }

    public SellerId getSellerId() {
        return sellerId;
    }

    public void setSellerId(SellerId sellerId) {
        this.sellerId = sellerId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPostType() {
        return postType;
    }

    public void setPostType(String postType) {
        this.postType = postType;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getStuffInfo() {
        return stuffInfo;
    }

    public void setStuffInfo(String stuffInfo) {
        this.stuffInfo = stuffInfo;
    }

    public String getStuffName() {
        return stuffName;
    }

    public void setStuffName(String stuffName) {
        this.stuffName = stuffName;
    }

    public String getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(String transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public List<String> getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(List<String> imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }


    public String getProductState() {
        return productState;
    }

    public void setProductState(String productState) {
        this.productState = productState;
    }
}
