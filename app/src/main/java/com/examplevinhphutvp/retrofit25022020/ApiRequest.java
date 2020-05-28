package com.examplevinhphutvp.retrofit25022020;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiRequest {

    @GET("demo2.json")
   Call<Demo2> callDemo2();
}
