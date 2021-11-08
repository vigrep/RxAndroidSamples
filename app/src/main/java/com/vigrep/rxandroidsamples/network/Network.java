package com.vigrep.rxandroidsamples.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Network {

    private static ZhuangbiApi mZhuangbiApi;

    public static ZhuangbiApi getZhuangbiApi() {
        if (mZhuangbiApi == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://www.zhuangbi.info/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            mZhuangbiApi = retrofit.create(ZhuangbiApi.class);
        }
        return mZhuangbiApi;
    }
}
