package com.examplevinhphutvp.retrofit25022020;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiRequest {

    @GET("demo1.json")
    Call<Demo1> callDemo1();
}
