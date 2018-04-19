package com.irwan.cannadys.kudotest.service;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import static okhttp3.logging.HttpLoggingInterceptor.Level.HEADERS;

/**
 * Created by irwancannady on 8/27/17.
 */

public class RetrofitHelper {

    private Context mContext;
    private static RetrofitHelper sInstance = null;

    private RetrofitHelper(@NonNull Context context){
        mContext = context.getApplicationContext();
    }

    public static RetrofitHelper getInstance(@NonNull Context context){
        if (sInstance == null){
            sInstance = new RetrofitHelper(context.getApplicationContext());
        }
        return sInstance;
    }

    Retrofit provideRetrofit(){
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder()
                .addInterceptor(provideHttpLoggingInterceptor())
                .readTimeout(20, TimeUnit.SECONDS)
                .connectTimeout(20, TimeUnit.SECONDS);

        Gson gson = new GsonBuilder().setLenient().serializeNulls().create();
        return new Retrofit.Builder()
                .baseUrl("https://kudo-practical.herokuapp.com/")
                .client(clientBuilder.build())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    private HttpLoggingInterceptor provideHttpLoggingInterceptor() {
        HttpLoggingInterceptor httpLoggingInterceptor =
                new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                    @Override
                    public void log(String message) {
                        Log.d("Error Message",message);
                    }
                });
        httpLoggingInterceptor.setLevel(HEADERS);
        return httpLoggingInterceptor;
    }

}
