package com.example.cats.mvp.view;

import com.example.cats.mvp.model.entity.Cat;

public interface MainActivityView {
    void setCatItemFragment(Cat cat);

    void setCatsFragment();
}
