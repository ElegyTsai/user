package com.example.user.service;

import com.example.user.model.PublicMaterial;
import com.example.user.model.PublicMaterialBase;
import com.example.user.mapper.PublicMaterialMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

@Service
public class PublicMaterialService {
    @Autowired
    PublicMaterialMapper publicMaterialMapper;

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

    public int add(PublicMaterial publicMaterial) {
        return publicMaterialMapper.add(publicMaterial);
    }

    public int delByPid(String pid) {
        return publicMaterialMapper.delByPid(pid);
    }

}
