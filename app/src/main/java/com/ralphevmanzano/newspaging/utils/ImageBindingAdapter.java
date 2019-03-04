package com.ralphevmanzano.newspaging.utils;

import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import androidx.databinding.BindingAdapter;

public class ImageBindingAdapter {
    @BindingAdapter("url")
    public static void loadImageUrl(ImageView imageView, String url) {
        if (url != null && !url.trim().isEmpty()) {
            Picasso.get()
                   .load(url)
                   .centerInside()
                   .fit()
                   .into(imageView);
        }
    }
}
