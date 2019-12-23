package com.example.navigationdrawerdemo;

import com.example.navigationdrawerdemo.modal.Category;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Service {

    String APIKEY = "8B89B11A17E191149D5382B338602B6E";
    String BASEPATH = "http://interierdesign.webtechinfoway.pw/feed/";

    @FormUrlEncoded
    @POST("Category")
    Call<Category> getCategory(@Field("api_key") String apikey);
}
