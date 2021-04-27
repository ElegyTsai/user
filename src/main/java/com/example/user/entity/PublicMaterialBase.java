package com.example.user.entity;

import lombok.Data;

import java.awt.image.BufferedImage;

@Data
public class PublicMaterialBase {
    private String pid;
    private BufferedImage thumbnail;

    public void setThumbnail(BufferedImage thumbnail) {
        this.thumbnail = thumbnail;
    }
}