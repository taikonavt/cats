package com.example.cats.mvp.model.repository;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Transformations;

import com.example.cats.mvp.model.entity.Cat;
import com.example.cats.mvp.model.entity.room.RoomCat;
import com.example.cats.mvp.model.entity.room.db.CatDatabase;

import java.util.ArrayList;
import java.util.List;

public class RoomCache implements ICache {

    @Override
    public LiveData<List<Cat>> getCatsList() {
        LiveData<List<RoomCat>> roomCats = CatDatabase.getInstance().getCatDao()
                .getAll();
        return Transformations.map(roomCats, new Function<List<RoomCat>, List<Cat>>() {
            @Override
            public List<Cat> apply(List<RoomCat> input) {
                final List<Cat> catList = new ArrayList<>();
                for (RoomCat roomCat : input) {
                    catList.add(castCat(roomCat));
                }
                return catList;
            }
        });
    }

    // кешировать список и при новом старте приложения проверять только изменения
    @Override
    public void updateCatList(final List<Cat> catList) {
        List<RoomCat> roomCatList = CatDatabase.getInstance().getCatDao()
                .getAll().getValue();
        if (roomCatList == null){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    CatDatabase.getInstance().getCatDao().insert(castRoomCat(catList));
                }
            }).start();
        } else {
            for (int i = 0; i < catList.size(); i++) {
                Cat newCat = catList.get(i);
                for (int j = 0; j < roomCatList.size(); j++) {
                    Cat oldCat = castCat(roomCatList.get(j));
                    if (!newCat.equals(oldCat)) {
                        final RoomCat newRoomCat = castRoomCat(newCat);
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                CatDatabase.getInstance().getCatDao().update(newRoomCat);
                            }
                        });
                    }
                }
            }
        }
    }

    private Cat castCat(RoomCat roomCat) {
        return new Cat(roomCat.getName(), roomCat.getPictureUrl());
    }

    private RoomCat castRoomCat(Cat cat){
        return new RoomCat(cat.getName(), cat.getPictureUrl());
    }

    private List<RoomCat> castRoomCat(List<Cat> catList){
        List<RoomCat> roomCatList = new ArrayList<>();
        for (Cat cat : catList) {
            roomCatList.add(castRoomCat(cat));
        }
        return roomCatList;
    }
}
