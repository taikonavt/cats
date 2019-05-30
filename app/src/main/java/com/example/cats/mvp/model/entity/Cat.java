package com.example.cats.mvp.model.entity;

public class Cat {

    private String name;
    private String pictureUrl;

    public Cat(String name, String url) {
        this.name = name;
        this.pictureUrl = url;
    }

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
}
