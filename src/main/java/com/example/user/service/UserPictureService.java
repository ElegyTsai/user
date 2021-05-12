package com.example.user.service;

import com.alibaba.fastjson.JSONObject;
import com.example.user.mapper.UserPictureMapper;
import com.example.user.model.PublicMaterial;
import com.example.user.model.PublicMaterialBase;
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

    public void saveFile(JSONObject file, String filePathname) throws IOException {
        FileWriter fileWriter = new FileWriter(filePathname);
        fileWriter.write(file.toJSONString());
        fileWriter.flush();
        fileWriter.close();
    }

    public void saveThumbnail(byte[] thumbnail, String thumbnailPathname) throws IOException {
        File f= new File(thumbnailPathname);
        FileOutputStream fileoutputStream = new FileOutputStream(f);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream((fileoutputStream));
        bufferedOutputStream.write(thumbnail);
        bufferedOutputStream.close();
        fileoutputStream.close();
    }

    public void delete(String thumbnail_url, String file_url){
        File f1 = new File(thumbnail_url);
        f1.delete();
        File f2 = new File(file_url);
        f2.delete();
    }

    public List<UserPictureBase> queryById(String id) throws IOException {
        List<UserPicture> userPictures = userPictureMapper.queryById(id);
        UserPicture userPicture;
        List<UserPictureBase> userPictureBases = new ArrayList<>();

        for(int i=0; i < userPictures.size(); i++){
            UserPictureBase userPictureBase = new UserPictureBase();
            userPicture = userPictures.get(i);
            String pid = userPicture.getPid();
            userPictureBase.setPid(pid);

            //读取缩略图
            String thumbnail_url = userPicture.getThumbnail_url();
            File file = new File(thumbnail_url);
            FileInputStream inputStream = new FileInputStream(file);
            byte[] bytes = new byte[inputStream.available()];
            inputStream.read(bytes, 0, inputStream.available());
            inputStream.close();
            userPictureBase.setThumbnail(bytes);

            userPictureBases.add(userPictureBase);
        }
        return userPictureBases;
    }

    public String queryByPid(String pid) throws IOException {
        UserPicture userPicture = userPictureMapper.querybypid(pid);
        String file_url = userPicture.getFile_url();
        String line="";
        File file = new File(file_url);
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        while(line != null){
            line = bufferedReader.readLine();
            if(line == null){
                break;
            }
            stringBuilder.append(line.trim());
        }
        bufferedReader.close();
        return stringBuilder.toString();
    }

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
            String thumbnailPathname = thumbnailSavePath + pid + ".png";
            String filePathname = fileSavePath + pid;
            delete(thumbnailPathname, filePathname);
            saveFile(file, filePathname);
            saveThumbnail(thumbnail, thumbnailPathname);
            return 1;
        }
        else{
            pid = UUID.randomUUID().toString().replace("-","");
            String thumbnailPathname = thumbnailSavePath + pid + ".png";
            String filePathname = fileSavePath + pid;

            UserPicture userPicture = new UserPicture();
            userPicture.setFile_url(filePathname);
            userPicture.setThumbnail_url(thumbnailPathname);
            userPicture.setPid(pid);
            userPicture.setId(id);

            saveFile(file, filePathname);
            saveThumbnail(thumbnail, thumbnailPathname);
            return userPictureMapper.add(userPicture);
        }
    }

    public int delByPid(String pid){
        UserPicture userPicture = userPictureMapper.querybypid(pid);
        String thumbnail_url = userPicture.getThumbnail_url();
        String file_url = userPicture.getFile_url();
        delete(thumbnail_url, file_url);
        return userPictureMapper.delByPid(pid);
    }
}
