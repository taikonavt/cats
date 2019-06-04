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
    private int isUpdated;

    public RoomCat(@NonNull String name, String pictureUrl){
        this.name = name;
        this.pictureUrl = pictureUrl;
        this.isUpdated = 1;
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

    public int getIsUpdated() {
        return isUpdated;
    }

    public void setIsUpdated(int isUpdated) {
        this.isUpdated = isUpdated;
    }
}
