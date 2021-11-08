package com.vigrep.rxandroidsamples.network;

import com.vigrep.rxandroidsamples.model.ImageItem;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import java.util.List;

public interface ZhuangbiApi {

    @GET("search")
    Call<List<ImageItem>> getImageList(@Query("q") String query);
}
