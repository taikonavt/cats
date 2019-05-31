package com.example.cats.mvp.model.entity.room;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class RoomCat {

    @NonNull
    @PrimaryKey
    private String name;
    private String pictureUrl;

    public RoomCat(@NonNull String name, String pictureUrl){
        this.name = name;
        this.pictureUrl = pictureUrl;
    }


    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String url) {
        this.pictureUrl = url;
    }
}
