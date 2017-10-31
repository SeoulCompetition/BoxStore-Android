package com.b05studio.boxstore.model;

import com.google.firebase.database.Exclude;

/**
 * Created by Marcel on 11/7/2015.
 */
public class ChatMessage {

    private String message;
    private String sender;
    private String recipient;
    private String stuff_id;
    private String SellerUID;
    private String BuyerUID;
    private String step;

    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }

    public String getStuff_id() {
        return stuff_id;
    }

    public void setStuff_id(String stuff_id) {
        this.stuff_id = stuff_id;
    }

    public String getSellerUID() {
        return SellerUID;
    }

    public void setSellerUID(String sellerUID) {
        SellerUID = sellerUID;
    }

    public String getBuyerUID() {
        return BuyerUID;
    }

    public void setBuyerUID(String buyerUID) {
        BuyerUID = buyerUID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isSeen() {
        return seen;
    }

    public void setSeen(boolean seen) {
        this.seen = seen;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    private String type;
    private boolean seen;
    private long time;

    private String price;
    private String station;

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    private int mRecipientOrSenderStatus;

    public ChatMessage() {
    }

    public ChatMessage(String message, String sender, String recipient, String type) {
        this.message = message;
        this.recipient = recipient;
        this.sender = sender;
        this.type = type;
    }


    public void setRecipientOrSenderStatus(int recipientOrSenderStatus) {
        this.mRecipientOrSenderStatus = recipientOrSenderStatus;
    }


    public String getMessage() {
        return message;
    }

    public String getRecipient(){
        return recipient;
    }

    public String getSender(){
        return sender;
    }

    @Exclude
    public int getRecipientOrSenderStatus() {
        return mRecipientOrSenderStatus;
    }
}
