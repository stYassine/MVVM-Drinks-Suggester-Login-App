package com.example.mvvmapplogin.services;

import com.example.mvvmapplogin.models.LoginBody;
import com.example.mvvmapplogin.models.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ILoginService {

    // https://regres.in/api/login
    @POST("/api/login")
    Call<LoginResponse> login(@Body LoginBody loginBody);


}
