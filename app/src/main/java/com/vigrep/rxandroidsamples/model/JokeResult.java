package com.vigrep.rxandroidsamples.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class JokeResult {
    public int code;
    public String message;
    @SerializedName("result")
    public List<Joke> jokeList;
}
