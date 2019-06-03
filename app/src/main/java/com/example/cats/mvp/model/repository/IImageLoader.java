package com.example.cats.mvp.model.repository;

import android.view.View;

import com.example.cats.ui.customviews.CustomView;

public interface IImageLoader {

    void loadInto(String pictureUrl, View view, int imageWidth);
}
