package com.example.collegealertapplication.model;

import android.net.Uri;

public class ModelImgePicked {
    /*---Variables---*/
    private String id = "";
    private Uri imageUri = null;
    private String imageUrl = null;
    private Boolean fromInternet = false;

    /*---Empty constructor required for Firebase db---*/
    public void ModelImagePicked() {

    }

    /*---Constructor with all parameters---*/
    public void ModelImagePicked(String id, Uri imageUri, String imageUrl, Boolean fromInternet) {
        this.id = id;
        this.imageUri = imageUri;
        this.imageUrl = imageUrl;
        this.fromInternet = fromInternet;
    }

    /*---Getter & Setters---*/
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Uri getImageUri() {
        return imageUri;
    }

    public void setImageUri(Uri imageUri) {
        this.imageUri = imageUri;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Boolean getFromInternet() {
        return fromInternet;
    }

    public void setFromInternet(Boolean fromInternet) {
        this.fromInternet = fromInternet;
    }
}
