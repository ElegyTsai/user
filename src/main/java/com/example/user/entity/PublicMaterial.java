package com.example.user.entity;

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
}