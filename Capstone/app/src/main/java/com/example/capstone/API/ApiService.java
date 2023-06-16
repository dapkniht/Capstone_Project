package com.example.capstone.API;


import org.json.JSONArray;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    @GET("user/fruits")
    Call<FruitResponse> getAllFruit(
            @Header("Authorization") String token
    );

    @GET("user/fruits")
    Call<FruitResponse> getAllFruitByName(
            @Header("Authorization") String token,
            @Query("name") String name
    );

    @GET("user/fruits/{id}")
    Call<DetailResponse> getDescription(
            @Header("Authorization") String token,
            @Path("id") String id
    );

    @Multipart
    @POST("user/predict")
    Call<PredictResponse> getPredict(
            @Header("Authorization") String token,
            @Part MultipartBody.Part file
    );

    @POST("auth/login")
    Call<LoginResponse> getLogin(
            @Body LoginRequest request
    );

    @GET("user/history")
    Call<HistoryResponse> getHistory(
            @Header("Authorization") String token
    );

    @POST("auth/register")
    Call<RegisterResponse> getRegister(
            @Body LoginRequest request
    );
}
