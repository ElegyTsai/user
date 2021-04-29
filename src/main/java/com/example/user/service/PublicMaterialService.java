package com.example.user.service;

import com.example.user.model.PublicMaterial;
import com.example.user.model.PublicMaterialBase;
import com.example.user.mapper.PublicMaterialMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

@Service
public class PublicMaterialService {
    @Autowired
    PublicMaterialMapper publicMaterialMapper;

    @Value("${prop.upload-folder}")
    private String UPLOAD_FOLDER;

    public List<PublicMaterialBase> queryByCate(String category) throws IOException {
        List<PublicMaterial> publicMaterials = publicMaterialMapper.queryByCate(category);
        PublicMaterial publicMaterial;
        List<PublicMaterialBase> publicMaterialBases = new ArrayList<>();

        for(int i=0; i < publicMaterials.size(); i++){
            PublicMaterialBase publicMaterialBase = new PublicMaterialBase();
            publicMaterial = publicMaterials.get(i);
            String pid = publicMaterial.getPid();
            publicMaterialBase.setPid(pid);

            String thumbnail_url = publicMaterial.getThumbnail_url();
            File file = new File(thumbnail_url);
            FileInputStream inputStream = new FileInputStream(file);
            byte[] bytes = new byte[inputStream.available()];
            inputStream.read(bytes, 0, inputStream.available());
            publicMaterialBase.setThumbnail(bytes);

            publicMaterialBases.add(publicMaterialBase);
        }
        return publicMaterialBases;
    }

    public byte[] queryByPid(String pid) throws IOException {
        PublicMaterial publicMaterial = publicMaterialMapper.queryByPid(pid);
        String picture_url = publicMaterial.getPicture_url();
        File file = new File(picture_url);
        FileInputStream inputStream = new FileInputStream(file);
        byte[] bytes = new byte[inputStream.available()];
        inputStream.read(bytes, 0, inputStream.available());
        return bytes;
    }

    public int add(MultipartFile file, String category) throws IOException {
        PublicMaterial publicMaterial = new PublicMaterial();
        publicMaterial.setCategory(category);
        String pid = UUID.randomUUID().toString().replaceAll("-","");
        publicMaterial.setPid(pid);

        String savePath = UPLOAD_FOLDER;
        String pictureSavePath = savePath + "picture/" + category + "/";
        String thumbnailSavePath = savePath + "thumbnail/" + category + "/";
        File pictureSavePathFile = new File(pictureSavePath);
        File thumbnailSavePathFile = new File(thumbnailSavePath);
        if (!pictureSavePathFile.exists()) {
            pictureSavePathFile.mkdirs();
        }
        if (!thumbnailSavePathFile.exists()) {
            thumbnailSavePathFile.mkdirs();
        }
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
        String filename = pid + "." + suffix;
        String picturePathname = pictureSavePath+ filename;
        String thumbnailPathname = thumbnailSavePath + filename;
        publicMaterial.setPicture_url(picturePathname);
        publicMaterial.setThumbnail_url(thumbnailPathname);
        try {
            file.transferTo(new File(picturePathname));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
        return publicMaterialMapper.add(publicMaterial);
    }

    public int delByPid(String pid) {
        return publicMaterialMapper.delByPid(pid);
    }

}
