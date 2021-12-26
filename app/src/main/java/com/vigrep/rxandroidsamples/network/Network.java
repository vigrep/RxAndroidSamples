package com.vigrep.rxandroidsamples.network;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class Network {

    private static GankApi mGankApi;
    private static ZhuangbiApi mZhuangbiApi;
    private static OpenApi mOpenApi;

    public static GankApi getGankApi() {
        if (mGankApi == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://gank.io/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            mGankApi = retrofit.create(GankApi.class);
        }
        return mGankApi;
    }

    public static ZhuangbiApi getZhuangbiApi() {
        if (mZhuangbiApi == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://www.zhuangbi.info/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
            mZhuangbiApi = retrofit.create(ZhuangbiApi.class);
        }
        return mZhuangbiApi;
    }

    public static OpenApi getOpenApi() {
        if (mZhuangbiApi == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://api.apiopen.top/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
            mOpenApi = retrofit.create(OpenApi.class);
        }
        return mOpenApi;
    }
}
