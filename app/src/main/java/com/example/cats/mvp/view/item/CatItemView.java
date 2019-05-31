package com.example.cats.mvp.view.item;

import android.graphics.Bitmap;

public interface CatItemView {
    int getPos();

    void setImage(Bitmap bitmap);

    void setName(String string);
}
