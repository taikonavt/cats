package com.example.cats.mvp.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class Cat implements Parcelable {

    private String name;
    private String pictureUrl;

    public Cat(String name, String url) {
        this.name = name;
        this.pictureUrl = url;
    }

    protected Cat(Parcel in) {
        name = in.readString();
        pictureUrl = in.readString();
    }

    public static final Creator<Cat> CREATOR = new Creator<Cat>() {
        @Override
        public Cat createFromParcel(Parcel in) {
            return new Cat(in);
        }

        @Override
        public Cat[] newArray(int size) {
            return new Cat[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Cat)) return false;
        Cat that = (Cat) obj;
        return getName().equals(that.getName()) &&
                getPictureUrl().equals(that.getPictureUrl());
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + getName().hashCode();
        result = 31 * result + getPictureUrl().hashCode();
        return result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(pictureUrl);
    }
}
