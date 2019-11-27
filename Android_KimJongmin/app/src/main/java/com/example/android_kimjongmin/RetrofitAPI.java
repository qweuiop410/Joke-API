package com.example.android_kimjongmin;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitAPI {

    //받아올 데이터 수
    @GET("20")
    Call<JokeListVO> getJoke();
}
