package com.example.cats.mvp.model.repository;

import android.widget.ImageView;

public interface IImageLoader {
    void loadInto(String pictureUrl, ImageView image, int imageWidth);
}
