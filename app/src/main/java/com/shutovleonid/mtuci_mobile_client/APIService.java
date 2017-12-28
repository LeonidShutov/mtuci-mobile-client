package com.shutovleonid.mtuci_mobile_client;

import retrofit2.Call;
import retrofit2.http.Body;

import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIService {

    @POST("/data")
    Call<String> savePost(@Body String data);

    @GET("/data")
    Call<String> getPost();
}