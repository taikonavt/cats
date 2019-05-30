package com.example.cats.mvp.presenter;

import com.example.cats.mvp.model.Repository;

public class CatListPresenter {

    private Repository repository;

    public CatListPresenter(){
        repository = new Repository();
    }
}
