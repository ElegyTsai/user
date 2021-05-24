package com.example.user.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/colormatch")
public class ColorMatchingController {
    @Value("${prop.upload-folder}")
    private String UPLOAD_FOLDER;

    @Value("${python.colormatching}")
    private String file;

    @RequestMapping("/")
    @ResponseBody
    public String colorMatching(String id, String pid1, String pid2, Integer k, String mode) {
        String savePath = UPLOAD_FOLDER;
        String thumbnailSavePath = savePath  + id + "/thumbnail/";
        String fileSavePath = savePath + id + "/file/";
        String[] args = new String[] {"python", file, "D:\\GRADUATE\\project\\picture_management\\bird\\bird1.png",
                                    "D:\\GRADUATE\\project\\picture_management\\bird\\bird2.png",
                                    "D:\\GRADUATE\\project\\picture_management", String.valueOf(8), "match"};
        try {
            Process process = Runtime.getRuntime().exec(args);
            return "success";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "fail";
    }
}
