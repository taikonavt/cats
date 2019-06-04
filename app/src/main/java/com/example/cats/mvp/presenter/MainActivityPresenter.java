package com.example.cats.mvp.presenter;

import com.example.cats.mvp.model.repository.Repository;
import com.example.cats.mvp.view.MainActivityView;

public class MainActivityPresenter {

    private MainActivityView mainView;
    private Repository repository;

    public MainActivityPresenter(MainActivityView mainView){
        this.mainView = mainView;
        repository = new Repository();
    }

    public void onFirstCreate() {
        repository.updateCatsList();
        this.mainView.setCatsFragment();
    }
}
