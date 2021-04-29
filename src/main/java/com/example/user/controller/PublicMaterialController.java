package com.example.user.controller;

import com.example.user.model.PublicMaterialBase;
import com.example.user.service.PublicMaterialService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@RestController
@RequestMapping("/material/public")
public class PublicMaterialController {
        @Autowired
        PublicMaterialService publicMaterialService;

        @RequestMapping(value="/querybycate", produces = {MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE})
         void queryByCate(String category, HttpServletRequest request, HttpServletResponse response)
                throws IOException {
            List<PublicMaterialBase> publicMaterialBases = publicMaterialService.queryByCate(category);
            String json = "";
            ObjectMapper objectMapper = new ObjectMapper ();
            json = objectMapper.writeValueAsString (publicMaterialBases); //java对象转换为json数据
            PrintWriter writer = response.getWriter ();
            writer.print (json);
            writer.flush ();
            writer.close ();
        }

        @RequestMapping(value="/querybypid", produces = {MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE})
        void queryByPid(String pid, HttpServletRequest request, HttpServletResponse response) throws IOException {
            byte[] picture = publicMaterialService.queryByPid(pid);
            String json = "";
            ObjectMapper objectMapper = new ObjectMapper ();
            json = objectMapper.writeValueAsString (picture); //java对象转换为json数据
            PrintWriter writer = response.getWriter ();
            writer.print (json);
            writer.flush ();
            writer.close ();
        }

        @RequestMapping("/add")
        String add(MultipartFile file, String category) throws IOException {
            String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
            if (file == null) {
                return "请选择要上传的图片";
            }
            if (category == null){
                return "请选择图片类别";
            }
            if (!"jpg,png".toUpperCase().contains(suffix.toUpperCase())) {
                return "请选择jpg,png格式的图片";
            }
            return publicMaterialService.add(file, category) == 1 ? "success" : "failed";
        }

        @RequestMapping("/delbypid")
        String delByPid(String pid) {
            return publicMaterialService.delByPid(pid) == 1 ? "success" : "failed";
        }
}
