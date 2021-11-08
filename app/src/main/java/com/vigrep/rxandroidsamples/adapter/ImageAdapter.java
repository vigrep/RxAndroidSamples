package com.vigrep.rxandroidsamples.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.vigrep.rxandroidsamples.databinding.ItemGridBinding;
import com.vigrep.rxandroidsamples.model.ImageItem;

import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {

    private List<ImageItem> mItems;

    public void setItems(List<ImageItem> items) {
        mItems = items;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemGridBinding.inflate(LayoutInflater.from(parent.getContext())));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ImageItem item = mItems.get(position);
        Glide.with(holder.mBinding.imgIv.getContext()).load(item.getImageUrl()).into(holder.mBinding.imgIv);
        holder.mBinding.titleTv.setText(item.getDescription());
    }

    @Override
    public int getItemCount() {
        return mItems != null ? mItems.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ItemGridBinding mBinding;

        public ViewHolder(ItemGridBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }
    }
}
