package com.example.user.model;

import lombok.Data;

@Data
public class PublicMaterialBase {
    private String pid;
    private byte[] thumbnail;

    public void setThumbnail(byte[] thumbnail) {
        this.thumbnail = thumbnail;
    }
}