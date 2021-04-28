package com.example.user.controller;

import com.example.user.model.PublicMaterial;
import com.example.user.model.PublicMaterialBase;
import com.example.user.service.PublicMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/material/public")
public class PublicMaterialController {
        @Autowired
        PublicMaterialService publicMaterialService;

        @RequestMapping(value="/querybycate", produces = {MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE})
        @ResponseBody
        List<PublicMaterialBase> queryByCate(String category) throws IOException {
            return publicMaterialService.queryByCate(category);
        }

        @RequestMapping(value="/querybypid", produces = {MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE})
        @ResponseBody
        byte[] queryByPid(String pid) throws IOException {
            return publicMaterialService.queryByPid(pid);
        }

        @RequestMapping("/add")
        String add(PublicMaterial publicMaterial) {
            return publicMaterialService.add(publicMaterial) == 1 ? "success" : "failed";
        }

        @RequestMapping("/delbypid")
        String delByPid(String pid) {
            return publicMaterialService.delByPid(pid) == 1 ? "success" : "failed";
        }
}
