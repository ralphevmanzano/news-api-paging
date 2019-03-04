package com.ralphevmanzano.newspaging.utils;

import com.ralphevmanzano.newspaging.vo.News;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

public class NewsDiffUtilCallBack extends DiffUtil.ItemCallback<News> {

    public NewsDiffUtilCallBack() {
    }

    @Override
    public boolean areItemsTheSame(@NonNull News oldItem, @NonNull News newItem) {
        return oldItem.getTitle().equals(newItem.getTitle());
    }

    @Override
    public boolean areContentsTheSame(@NonNull News oldItem, @NonNull News newItem) {
        return oldItem.getTitle().equals(newItem.getTitle()) &&
                oldItem.getPublishedAt().equals(newItem.getPublishedAt()) &&
                oldItem.getUrl().equals(newItem.getUrl());
    }
}
