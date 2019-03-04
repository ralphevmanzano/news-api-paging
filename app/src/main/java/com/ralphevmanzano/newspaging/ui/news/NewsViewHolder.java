package com.ralphevmanzano.newspaging.ui.news;

import android.view.View;

import com.ralphevmanzano.newspaging.BR;
import com.ralphevmanzano.newspaging.vo.News;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

class NewsViewHolder extends RecyclerView.ViewHolder {

    private ViewDataBinding binding;
    private OnNewsClickListener onNewsClickListener;

    NewsViewHolder(@NonNull ViewDataBinding binding, OnNewsClickListener onNewsClickListener) {
        super(binding.getRoot());
        this.binding = binding;
        this.onNewsClickListener = onNewsClickListener;
    }

    void onBind(News news) {
        binding.setVariable(BR.news, news);
        binding.executePendingBindings();

        binding.getRoot().setOnClickListener(v -> {
            onNewsClickListener.onNewsClick(news.getUrl());
        });
    }
}
