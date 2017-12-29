package com.shutovleonid.mtuci_mobile_client;

import retrofit2.Call;
import retrofit2.http.Body;

import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface APIService {

    @POST("/data/{id}")
    Call<String> savePost(@Path("id") String id, @Body String data);

    @GET("/data/{id}")
    Call<String> getPost(@Path("id") String id);
}