package com.b05studio.boxstore.service.response;

/**
 * Created by joyeongje on 2017. 10. 17..
 */

import com.b05studio.boxstore.model.Category;
import com.facebook.stetho.common.ArrayListAccumulator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class CategoryGetResponse {
    @SerializedName("result")
    @Expose
    private String result;
    @SerializedName("data")
    @Expose
    private List<Category> data = null;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public List<Category> getData() {
        return data;
    }

    public void setData(List<Category> data) {
        this.data = data;
    }
}


