package com.example.user.model;

import com.alibaba.fastjson.JSONObject;

public class UserPicture extends UserPictureBase{
    private String thumbnail_url;
    private String file_url;
    private String id;
    private JSONObject file;

    public String getThumbnail_url() {
        return thumbnail_url;
    }

    public void setThumbnail_url(String thumbnail_url) {
        this.thumbnail_url = thumbnail_url;
    }

    public String getFile_url() {
        return file_url;
    }

    public void setFile_url(String file_url) {
        this.file_url = file_url;
    }

    public void setId(String id) {
        this.id = id;
    }
}
