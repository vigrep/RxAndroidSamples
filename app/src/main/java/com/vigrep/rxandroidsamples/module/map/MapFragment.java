package com.vigrep.rxandroidsamples.module.map;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.vigrep.rxandroidsamples.adapter.JokeListAdapter;
import com.vigrep.rxandroidsamples.databinding.FragmentMapBinding;
import com.vigrep.rxandroidsamples.model.JokeResult;
import com.vigrep.rxandroidsamples.network.Network;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.List;

public class MapFragment extends Fragment {

    private static final String TAG = "MapFragment";

    private FragmentMapBinding mBinding;
    private JokeListAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mBinding = FragmentMapBinding.inflate(inflater);

        mAdapter = new JokeListAdapter();
        mBinding.jokeRv.setAdapter(mAdapter);
        mBinding.jokeRv.setLayoutManager(new LinearLayoutManager(getActivity()));

        Network.getOpenApi().getJokeList(1, 6, "text").enqueue(new Callback<JokeResult>() {
            @Override
            public void onResponse(Call<JokeResult> call, Response<JokeResult> response) {
                Log.d(TAG, "succ: " + response.body());
                mAdapter.setItems(response.body().jokeList);
            }

            @Override
            public void onFailure(Call<JokeResult> call, Throwable t) {
                Log.d(TAG, "error: " + t.getMessage());
            }
        });

//        practice();

        return mBinding.getRoot();
    }

    @SuppressLint("CheckResult")
    private void practice() {
        Log.d(TAG, "map:");
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
            }
        }).map(new Function<Integer, String>() {
            @Override
            public String apply(@NonNull Integer integer) throws Exception {
                return String.valueOf(integer);
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.d(TAG, s);
            }
        });

        Log.d(TAG, "flatMap:");

        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
            }
        }).flatMap(new Function<Integer, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(@NonNull Integer integer) throws Exception {
                List<String> stringList = new ArrayList<>();
                for (int i = 0; i < integer; i++) {
                    stringList.add(i + " + flatMap");
                }
                return Observable.fromIterable(stringList);
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.d(TAG, s);
            }
        });

        Log.d(TAG, "concatMap:");
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
            }
        }).concatMap(new Function<Integer, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(@NonNull Integer integer) throws Exception {
                List<String> stringList = new ArrayList<>();
                for (int i = 0; i < integer; i++) {
                    stringList.add(i + " + concatMap");
                }
                return Observable.fromIterable(stringList);
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.d(TAG, s);
            }
        });

        Log.d(TAG, "buffer:");
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onNext(4);
                emitter.onNext(5);
            }
        }).buffer(3,1).map(new Function<List<Integer>, String>() {
            @Override
            public String apply(@NonNull List<Integer> integers) throws Exception {
                return String.valueOf(integers);
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.d(TAG, s);
            }
        });
    }
}