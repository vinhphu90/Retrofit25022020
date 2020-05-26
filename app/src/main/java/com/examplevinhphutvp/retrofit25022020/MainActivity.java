package com.examplevinhphutvp.retrofit25022020;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // cach su dung lid retrofit
        // 1 : khoi tao ra retrofit
        // OkHttpClient : Config các connecsion
        OkHttpClient okHttpClient =new  OkHttpClient.Builder()
                .readTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build();
        // Gson : parse dữ liệu về dạng object của java
        Gson gson = new GsonBuilder()
                .setLenient()
                .disableHtmlEscaping()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://khoapham.vn/KhoaPhamTraining/json/tien/")
                .client( okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();


        // 2 : Đinh nghĩa các request call http
        // 3 : gọi request muon thực thi
        ApiRequest apiRequest = retrofit.create(ApiRequest.class);

        Call<Demo1> demo1Call = apiRequest.callDemo1();
        demo1Call.enqueue(new Callback<Demo1>() {
            @Override
            public void onResponse(Call<Demo1> call, Response<Demo1> response) {
                Demo1 demo1 = response.body();
                Toast.makeText(MainActivity.this, demo1.getMonhoc(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<Demo1> call, Throwable t) {

            }
        });
        // 4 : nhận du liệu từ request thong qua phuong thức call
    }
}
