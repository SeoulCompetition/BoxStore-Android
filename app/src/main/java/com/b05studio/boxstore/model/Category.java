package com.b05studio.boxstore.model;

/**
 * Created by seungwoo on 2017-10-01.
 */

public class Category {

    private String imageURL;
    private String title;
    private String description;

    public Category(String imageURL,String title,String description) {
        this.imageURL = imageURL;
        this.title = title;
        this.description = description;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        imageURL = imageURL;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
