
package com.b05studio.boxstore.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Stuff {

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
    @SerializedName("receipt")
    @Expose
    private Receipt receipt;
    @SerializedName("negotiation")
    @Expose
    private Negotiation negotiation;
    @SerializedName("transactionStatus")
    @Expose
    private String transactionStatus;
    @SerializedName("imageUrl")
    @Expose
    private List<String> imageUrl = null;
    @SerializedName("createdDate")
    @Expose
    private String createdDate;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Stuff() {
    }

    /**
     * 
     * @param postType
     * @param imageUrl
     * @param stationId
     * @param receipt
     * @param id
     * @param category
     * @param price
     * @param sellerId
     * @param negotiation
     * @param stuffName
     * @param createdDate
     * @param transactionStatus
     * @param stuffInfo
     */
    public Stuff(String id, StationId stationId, SellerId sellerId, String category, String postType, Integer price, String stuffInfo, String stuffName, Receipt receipt, Negotiation negotiation, String transactionStatus, List<String> imageUrl, String createdDate) {
        super();
        this.id = id;
        this.stationId = stationId;
        this.sellerId = sellerId;
        this.category = category;
        this.postType = postType;
        this.price = price;
        this.stuffInfo = stuffInfo;
        this.stuffName = stuffName;
        this.receipt = receipt;
        this.negotiation = negotiation;
        this.transactionStatus = transactionStatus;
        this.imageUrl = imageUrl;
        this.createdDate = createdDate;
    }

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

    public Receipt getReceipt() {
        return receipt;
    }

    public void setReceipt(Receipt receipt) {
        this.receipt = receipt;
    }

    public Negotiation getNegotiation() {
        return negotiation;
    }

    public void setNegotiation(Negotiation negotiation) {
        this.negotiation = negotiation;
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

}
