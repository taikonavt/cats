package com.example.cats.mvp.model.api;

import com.example.cats.mvp.model.entity.Cat;

import java.util.List;

public interface IDataSource {

    List<Cat> getCatList();
}
