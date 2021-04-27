package com.example.user.entity;

import lombok.Data;

import java.awt.image.BufferedImage;

@Data
public class PublicMaterialBase {
    private String pid;
    private byte[] thumbnail;

    public void setThumbnail(byte[] thumbnail) {
        this.thumbnail = thumbnail;
    }
}