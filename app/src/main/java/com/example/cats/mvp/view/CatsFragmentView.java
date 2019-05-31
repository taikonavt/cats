package com.example.cats.mvp.view;

import android.arch.lifecycle.LiveData;

import com.example.cats.mvp.model.entity.Cat;

import java.util.List;

public interface CatsFragmentView {
    void setCatItemFragment(Cat cat);

    void observeData(LiveData<List<Cat>> data);
}
