package com.b05studio.boxstore.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by seungwoo on 2017-10-01.
 */

public class Category {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("parent")
    @Expose
    private String parent;
    @SerializedName("description")
    @Expose
    private String description;


    public Category(String name, String parent, String description) {
        this.name = name;
        this.parent = parent;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
