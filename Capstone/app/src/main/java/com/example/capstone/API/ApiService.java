package com.example.capstone.API;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    @GET("user/fruits")
    Call<FruitResponse> getAllFruit();

    @GET("user/fruits")
    Call<FruitResponse> getAllFruitByName(
            @Query("name") String name
    );

    @GET("user/fruits/{id}")
    Call<DetailResponse> getDescription(
            @Path("id") String id
    );
}
