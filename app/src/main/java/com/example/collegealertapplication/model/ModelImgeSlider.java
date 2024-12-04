package com.example.collegealertapplication.model;

public class ModelImgeSlider {
    String id, imageUrl;

    //empty constructor required for firebase db
    public void ModelImageSlider() {

    }

    public void ModelImageSlider(String id, String imageUrl) {
        this.id = id;
        this.imageUrl = imageUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
