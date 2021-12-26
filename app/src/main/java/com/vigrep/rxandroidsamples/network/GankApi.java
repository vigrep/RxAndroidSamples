package com.vigrep.rxandroidsamples.network;

import com.vigrep.rxandroidsamples.model.ImageItem;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface GankApi {

    @GET("data/福利/{number}/{page}")
    Call<ImageItem> getImage(@Path("number") int number, @Path("page") int page);
}
