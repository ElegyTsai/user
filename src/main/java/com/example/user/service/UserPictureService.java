package com.example.user.service;

import com.alibaba.fastjson.JSONObject;
import com.example.user.mapper.UserPictureMapper;
import com.example.user.model.UserPicture;
import com.example.user.model.UserPictureBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserPictureService {
    @Autowired
    UserPictureMapper userPictureMapper;

    @Value("${prop.upload-folder}")
    private String UPLOAD_FOLDER;

    public List<UserPictureBase> queryById(String id) throws IOException {
        List<UserPicture> userPictures = userPictureMapper.queryById(id);
        List<UserPictureBase> userPictureBases = new ArrayList<>();
        UserPicture userPicture;

        for(int i=0; i<userPictures.size(); i++){
            UserPictureBase userPictureBase = new UserPictureBase();
            userPicture = userPictures.get(i);
            String pid = userPicture.getPid();
            userPictureBase.setPid(pid);

            String thumbnail_url = userPicture.getThumbnail_url();
            File file = new File(thumbnail_url);
            FileInputStream inputStream = new FileInputStream(file);
            byte[] bytes = new byte[inputStream.available()];
            inputStream.read(bytes, 0, inputStream.available());
            userPictureBase.setThumbnail(bytes);

            userPictureBases.add(userPictureBase);
        }
        return userPictureBases;
    }

//    public JSONObject queryByPid(String pid){
//        UserPicture userPicture = userPictureMapper.querybypid(pid);
//        String file_url = userPicture.getFile_url();
//        File file = new File(file_url);
//    }

    public int save(JSONObject file, byte[] thumbnail, String id, String pid) throws IOException {
        String savePath = UPLOAD_FOLDER;
        String thumbnailSavePath = savePath  + id + "/thumbnail/";
        String fileSavePath = savePath + id + "/file/";

        File thumbnailSavePathFile = new File(thumbnailSavePath);
        File fileSavePathFile = new File(fileSavePath);
        if (!thumbnailSavePathFile.exists()) {
            thumbnailSavePathFile.mkdirs();
        }
        if (!fileSavePathFile.exists()) {
            fileSavePathFile.mkdirs();
        }

        if(pid!=""){
            String thumbnailPathname = thumbnailSavePath + pid;
            String filePathname = fileSavePath + pid;
            File f1 = new File(thumbnailPathname);
            f1.delete();
            File f2 = new File(filePathname);
            f2.delete();

            FileWriter fileWriter = new FileWriter(filePathname);
            fileWriter.write(file.toJSONString());

            File f= new File(thumbnailPathname);
            FileOutputStream outputStream = new FileOutputStream(f);
            outputStream.write(thumbnail);
            return 1;
        }
        else{
            UserPicture userPicture = new UserPicture();
            pid = UUID.randomUUID().toString().replace("-","");
            String thumbnailPathname = thumbnailSavePath + pid;
            String filePathname = fileSavePath + pid;
            userPicture.setFile_url(filePathname);
            userPicture.setThumbnail_url(thumbnailPathname);
            userPicture.setPid(pid);
            userPicture.setId(id);
            FileWriter fileWriter = new FileWriter(filePathname);
            fileWriter.write(file.toJSONString());

            File f= new File(thumbnailPathname);
            FileOutputStream outputStream = new FileOutputStream(f);
            outputStream.write(thumbnail);
            return userPictureMapper.add(userPicture);
        }
    }
}
