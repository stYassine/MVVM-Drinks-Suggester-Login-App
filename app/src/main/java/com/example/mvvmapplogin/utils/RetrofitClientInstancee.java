package com.example.mvvmapplogin.utils;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientInstancee {

    private static final String BASE_URL = "https://regres.in/";
    private static Retrofit retrofit;

    public static Retrofit getRetrofit() {
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
