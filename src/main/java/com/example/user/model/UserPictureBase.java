package com.example.user.model;

import lombok.Data;

@Data
public class UserPictureBase {
    private String pid;
    private byte[] thumbnail;

    public void setThumbnail(byte[] thumbnail) {
        this.thumbnail = thumbnail;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getPid() {
        return pid;
    }
}
