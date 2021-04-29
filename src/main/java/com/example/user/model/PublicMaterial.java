package com.example.user.model;

import lombok.Data;

@Data
public class PublicMaterial extends PublicMaterialBase{
    private String thumbnail_url;
    private String picture_url;
    private String category;

    public String getThumbnail_url() {
        return thumbnail_url;
    }

    public String getPicture_url() {
        return picture_url;
    }

    public void setThumbnail_url(String thumbnail_url) {
        this.thumbnail_url = thumbnail_url;
    }

    public void setPicture_url(String picture_url) {
        this.picture_url = picture_url;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}