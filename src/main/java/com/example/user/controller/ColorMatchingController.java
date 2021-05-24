package com.example.user.controller;

import com.example.user.mapper.PublicMaterialMapper;
import com.example.user.model.PublicMaterial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/api/colormatch")
public class ColorMatchingController {
    @Autowired
    PublicMaterialMapper publicMaterialMapper;

    @Value("${prop.upload-folder}")
    private String UPLOAD_FOLDER;

    @Value("${python.colormatching}")
    private String file;

    @RequestMapping("/")
    @ResponseBody
    public String colorMatching(String id, String pid1, String pid2,
                                @RequestParam(value = "k", required = false, defaultValue = "8")Integer k,
                                @RequestParam(value = "mode", required = false, defaultValue = "match")String mode) {
        String savePath = UPLOAD_FOLDER;
        String thumbnailSavePath = savePath  + id + "/thumbnail/";
        String pictureSavePath = savePath + id + "/picture/";
        File thumbnailSavePathFile = new File(thumbnailSavePath);
        File pictureSavePathFile = new File(pictureSavePath);
        if (!thumbnailSavePathFile.exists()) {
            thumbnailSavePathFile.mkdirs();
        }
        if (!pictureSavePathFile.exists()) {
            pictureSavePathFile.mkdirs();
        }

        PublicMaterial publicMaterial1 = publicMaterialMapper.queryByPid(pid1);
        PublicMaterial publicMaterial2 = publicMaterialMapper.queryByPid(pid2);

        String reference = publicMaterial1.getPicture_url();
        String source = publicMaterial2.getPicture_url();
        String[] args = new String[] {"python", file, reference, source, pictureSavePath,
                                    String.valueOf(k), mode};
        try {
            Process process = Runtime.getRuntime().exec(args);
            return "success";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "fail";
    }
}
