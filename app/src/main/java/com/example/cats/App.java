package com.example.cats;

import android.app.Application;

import com.example.cats.mvp.model.entity.room.db.CatDatabase;

public class App extends Application {

    static private App instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        CatDatabase.create(this);
    }

    public static App getInstance(){
        return instance;
    }
}
