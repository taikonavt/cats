package com.example.cats.mvp.model.entity.room.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.cats.mvp.model.entity.room.RoomCat;
import com.example.cats.mvp.model.entity.room.dao.CatDao;

@Database(entities = RoomCat.class, version = 1)
public abstract class CatDatabase extends RoomDatabase {

    private static final String DB_NAME = "catDatabase.db";
    private static volatile CatDatabase instance;

    public static synchronized CatDatabase getInstance(){
        if(instance == null){
            throw new RuntimeException("Database has not been created. Please call create()");
        }
        return instance;
    }

    public static void create(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context, CatDatabase.class, DB_NAME).build();
        }
    }

    public abstract CatDao getCatDao();
}
