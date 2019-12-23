package com.example.navigationdrawerdemo.modal;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SubCategory {

    @SerializedName("data")
    @Expose
    public List<information> data = null;

    public class information {

        @SerializedName("id")
        @Expose
        public String id;

        @SerializedName("category_id")
        @Expose
        public String category_id;

        @SerializedName("subcategory_name")
        @Expose
        public String subcategory_name;

    }
}