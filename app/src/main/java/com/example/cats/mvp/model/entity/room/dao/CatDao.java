package com.example.cats.mvp.model.entity.room.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.cats.mvp.model.entity.room.RoomCat;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface CatDao {

    @Insert(onConflict = REPLACE)
    void insert(RoomCat cat);

    @Insert(onConflict = REPLACE)
    void insert(RoomCat... cats);

    @Insert(onConflict = REPLACE)
    void insert(List<RoomCat> cats);


    @Update
    void update(RoomCat cat);

    @Update
    void update(RoomCat... cats);

    @Update
    void update(List<RoomCat> cats);


    @Delete
    void delete(RoomCat cat);

    @Delete
    void delete(RoomCat... cats);

    @Delete
    void delete(List<RoomCat> cats);


    @Query("SELECT * FROM roomcat")
    LiveData<List<RoomCat>> getAll();

    @Query("SELECT * FROM roomcat WHERE url = :url LIMIT 1")
    RoomCat findByUrl(String url);
}
