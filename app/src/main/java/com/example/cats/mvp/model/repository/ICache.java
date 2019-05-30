package com.example.cats.mvp.model.repository;

import android.arch.lifecycle.LiveData;

import com.example.cats.mvp.model.entity.Cat;

import java.util.List;

public interface ICache {
    LiveData<List<Cat>> getCatsList();

    void updateCatList(List<Cat> catList);
}
