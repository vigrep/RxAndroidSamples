package com.vigrep.rxandroidsamples.module.elementary;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import com.vigrep.rxandroidsamples.adapter.ImageAdapter;
import com.vigrep.rxandroidsamples.databinding.FragmentElementaryBinding;
import com.vigrep.rxandroidsamples.model.ImageItem;
import com.vigrep.rxandroidsamples.network.Network;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;

public class ElementaryFragment extends Fragment implements RadioGroup.OnCheckedChangeListener {

    private static final String TAG = "ElementaryFragment";

    private FragmentElementaryBinding mBinding;
    private ImageAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = FragmentElementaryBinding.inflate(getLayoutInflater());

        mAdapter = new ImageAdapter();
        mBinding.gridRv.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        mBinding.gridRv.setAdapter(mAdapter);

        mBinding.tagRg.setOnCheckedChangeListener(this);

        mBinding.swipeRefreshLayout.setEnabled(false);

        return mBinding.getRoot();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        RadioButton rb = mBinding.getRoot().findViewById(checkedId);
        String key = rb.getText().toString();
        search(key);
    }

    private void search(String key) {
        mBinding.swipeRefreshLayout.setRefreshing(true);
        Network.getZhuangbiApi().getImageList(key).enqueue(new Callback<List<ImageItem>>() {
            @Override
            public void onResponse(Call<List<ImageItem>> call, Response<List<ImageItem>> response) {
                mBinding.swipeRefreshLayout.setRefreshing(false);
                Log.d(TAG, "succ: " + response.body());
                mAdapter.setItems(response.body());
            }

            @Override
            public void onFailure(Call<List<ImageItem>> call, Throwable t) {
                Log.d(TAG, "error: " + t.getMessage());
                mBinding.swipeRefreshLayout.setRefreshing(false);
            }
        });
    }
}
