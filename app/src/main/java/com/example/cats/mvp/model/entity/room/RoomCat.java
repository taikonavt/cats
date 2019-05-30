package com.example.cats.mvp.model.entity.room;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class RoomCat {

    @NonNull
    @PrimaryKey
    private String name;
    private String url;

    public RoomCat(@NonNull String name, String url){
        this.name = name;
        this.url = url;
    }


    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public String getPictureUrl() {
        return url;
    }

    public void setPictureUrl(String url) {
        this.url = url;
    }
}
