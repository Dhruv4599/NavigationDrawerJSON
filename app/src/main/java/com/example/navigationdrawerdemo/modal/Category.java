package com.example.navigationdrawerdemo.modal;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Category {

    @SerializedName("data")
    @Expose
    public List<information> data = null;

    public class information {

        @SerializedName("id")
        @Expose
        public String id;
        @SerializedName("category_name")
        @Expose
        public String categoryName;

    }
}