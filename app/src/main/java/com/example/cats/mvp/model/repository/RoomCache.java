package com.example.cats.mvp.model.repository;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Transformations;

import com.example.cats.mvp.model.entity.Cat;
import com.example.cats.mvp.model.entity.room.RoomCat;
import com.example.cats.mvp.model.entity.room.dao.CatDao;
import com.example.cats.mvp.model.entity.room.db.CatDatabase;

import java.util.ArrayList;
import java.util.List;

public class RoomCache implements ICache {

    @Override
    public LiveData<List<Cat>> getCatsList() {
        LiveData<List<RoomCat>> roomCats = CatDatabase.getInstance().getCatDao()
                .getLiveAll();
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
    public void updateCatList(final List<Cat> newCatList) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                CatDao catDao = CatDatabase.getInstance().getCatDao();
                catDao.setAllIsNotUpdated();
                final List<RoomCat> oldRoomCatList = catDao.getAll();
                if (oldRoomCatList == null){
                    List<RoomCat> newRoomCatList = castRoomCat(newCatList);
                    catDao.insert(newRoomCatList);
                } else {
                    for (int i = 0; i < newCatList.size(); i++) {
                        final Cat newCat = newCatList.get(i);
                        RoomCat oldRoomCat = catDao.findByName(newCat.getName());
                        RoomCat newRoomCat = castRoomCat(newCat);
                        if (oldRoomCat != null){
                            Cat oldCat = castCat(oldRoomCat);
                            if (!newCat.equals(oldCat)){
                                catDao.update(newRoomCat);
                            } else {
                                oldRoomCat.setIsUpdated(1);
                                catDao.update(oldRoomCat);
                            }
                        } else {
                            catDao.insert(newRoomCat);
                        }
                    }
                }
                catDao.deleteNotUpdated();
            }
        }).start();
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
