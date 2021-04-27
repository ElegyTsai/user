package com.example.user.controller;

import com.example.user.entity.PublicMaterial;
import com.example.user.entity.PublicMaterialBase;
import com.example.user.mapper.PublicMaterialMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/material/public")
public class PublicMaterialController {
    @Autowired
    PublicMaterialMapper publicMaterialMapper;

    @RequestMapping(value="/querybycate", produces = {MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE})
    @ResponseBody
    List<PublicMaterialBase> queryByCate(String category) throws IOException {
        List<PublicMaterial> publicMaterials = publicMaterialMapper.queryByCate(category);
        PublicMaterial publicMaterial;
        for(int i=0; i < publicMaterials.size(); i++){
            publicMaterial = publicMaterials.get(i);
            String thumbnail_url = publicMaterial.getThumbnail_url();
            File file = new File(thumbnail_url);
            FileInputStream inputStream = new FileInputStream(file);
            byte[] bytes = new byte[inputStream.available()];
            inputStream.read(bytes, 0, inputStream.available());
            publicMaterial.setThumbnail(bytes);
        }
        List<PublicMaterialBase> publicMaterialBases = new ArrayList<>();
        publicMaterialBases.addAll(publicMaterials);
        return publicMaterialBases;
    }

    @RequestMapping(value="/querybypid", produces = {MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE})
    @ResponseBody
    byte[] queryByPid(String pid) throws IOException {
        PublicMaterial publicMaterial = publicMaterialMapper.queryByPid(pid);
        String picture_url = publicMaterial.getPicture_url();
        File file = new File(picture_url);
        FileInputStream inputStream = new FileInputStream(file);
        byte[] bytes = new byte[inputStream.available()];
        inputStream.read(bytes, 0, inputStream.available());
        return bytes;
    }

    @RequestMapping("/add")
    String add(PublicMaterial publicMaterial) {
        return publicMaterialMapper.add(publicMaterial) == 1 ? "success" : "failed";
    }

    @RequestMapping("/delbypid")
    String delByPid(String pid) {
        return publicMaterialMapper.delByPid(pid) == 1 ? "success" : "failed";
    }

}
