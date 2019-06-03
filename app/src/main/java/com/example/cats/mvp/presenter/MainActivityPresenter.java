package com.example.cats.mvp.presenter;

import com.example.cats.mvp.view.MainActivityView;

public class MainActivityPresenter {

    private MainActivityView mainView;

    public MainActivityPresenter(MainActivityView mainView){
        this.mainView = mainView;
    }

    public void onFirstCreate() {
        this.mainView.setCatsFragment();
    }
}
