package com.vigrep.rxandroidsamples.network;

import com.vigrep.rxandroidsamples.model.ImageItem;
import com.vigrep.rxandroidsamples.model.JokeResult;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface OpenApi {
    //getJoke?page=1&count=3&type=video
    @GET("getJoke")
    Call<JokeResult> getJokeList(@Query("page") int page, @Query("count") int count, @Query("type") String type);
}
