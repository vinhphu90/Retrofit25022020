package com.examplevinhphutvp.retrofit25022020;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;
import java.util.List;
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
        ImageView mImg ;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mImg = findViewById(R.id.imageview);

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
        Call<List<Demo4>> demo4Call = apiRequest.callDemo4();
        demo4Call.enqueue(new Callback<List<Demo4>>() {
            @Override
            public void onResponse(Call<List<Demo4>> call, Response<List<Demo4>> response) {
                List<Demo4> demo4List = response.body();
                Toast.makeText(MainActivity.this, demo4List.size() + "", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<Demo4>> call, Throwable t) {

            }
        });

        // 4 : nhận du liệu từ request thong qua phuong thức call
    }

    private void callApiDemo2(ApiRequest apiRequest) {

        Call<Demo2> demo2Call = apiRequest.callDemo2();
        demo2Call.enqueue(new Callback<Demo2>() {
            @Override
            public void onResponse(Call<Demo2> call, Response<Demo2> response) {
                Demo2 demo2 = response.body();
                ImageView mImg = null;
                Glide.with(MainActivity.this)
                        .load(demo2.getDanhsach())
                        .placeholder(R.mipmap.ic_launcher)
                        .error(R.drawable.ic_launcher_background)
                        .into(mImg);

            }

            @Override
            public void onFailure(Call<Demo2> call, Throwable t) {
                Log.d("BBB",t.getMessage());
            }
        });

    }
}
