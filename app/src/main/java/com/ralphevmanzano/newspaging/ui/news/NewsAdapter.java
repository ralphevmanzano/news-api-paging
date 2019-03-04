package com.ralphevmanzano.newspaging.ui.news;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.ralphevmanzano.newspaging.R;
import com.ralphevmanzano.newspaging.vo.News;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;

public class NewsAdapter extends PagedListAdapter<News, NewsViewHolder> {

    private OnNewsClickListener onNewsClickListener;

    public NewsAdapter(@NonNull DiffUtil.ItemCallback<News> diffCallback, OnNewsClickListener onNewsClickListener) {
        super(diffCallback);
        this.onNewsClickListener = onNewsClickListener;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.news_list_item, parent, false);
        return new NewsViewHolder(binding, onNewsClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        holder.onBind(getItem(holder.getAdapterPosition()));
    }
}
