package com.example.cats.mvp.model.repository;

import android.arch.lifecycle.LiveData;

import com.example.cats.mvp.model.api.DataSource;
import com.example.cats.mvp.model.api.IDataSource;
import com.example.cats.mvp.model.entity.Cat;

import java.util.List;

public class Repository {

    private IDataSource dataSource;
    private ICache cache;

    public Repository(){
        dataSource = new DataSource();
        cache = new RoomCache();
    }

    public LiveData<List<Cat>> getCatsList(){
        return cache.getCatsList();
    }

    public void updateCatsList(){
        dataSource = new DataSource();
        List<Cat> catList = dataSource.getCatList();
        cache.updateCatList(catList);
    }
}
