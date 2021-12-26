package com.vigrep.rxandroidsamples.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.vigrep.rxandroidsamples.databinding.ItemJokeBinding;
import com.vigrep.rxandroidsamples.model.Joke;

import java.util.List;

public class JokeListAdapter extends RecyclerView.Adapter<JokeListAdapter.ViewHolder> {

    private List<Joke> mJokeList;

    public void setItems(List<Joke> jokeList) {
        mJokeList = jokeList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemJokeBinding.inflate(LayoutInflater.from(parent.getContext())));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Joke joke = mJokeList.get(position);
        holder.mBinding.titleTv.setText(joke.text);
    }

    @Override
    public int getItemCount() {
        return mJokeList != null ? mJokeList.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ItemJokeBinding mBinding;

        public ViewHolder(ItemJokeBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }
    }
}
