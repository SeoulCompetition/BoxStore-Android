package com.b05studio.boxstore.service.response;

/**
 * Created by joyeongje on 2017. 10. 17..
 */

import com.b05studio.boxstore.model.Category;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CategoryGetResponse {

    @SerializedName("RESULT")
    @Expose
    private String rESULT;

    @SerializedName("DATA")
    @Expose
    private List<Category> categories = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public CategoryGetResponse() {
    }


    public CategoryGetResponse(String rESULT, List<Category> categories) {
        super();
        this.rESULT = rESULT;
        this.categories = categories;
    }

    public String getRESULT() {
        return rESULT;
    }

    public void setRESULT(String rESULT) {
        this.rESULT = rESULT;
    }

    public List<Category> getDATA() {
        return categories;
    }

    public void setDATA(List<Category> categories) {
        this.categories = categories;
    }

}

