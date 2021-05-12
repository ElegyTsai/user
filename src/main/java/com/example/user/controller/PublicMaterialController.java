package com.example.user.controller;

import com.example.user.model.PublicMaterialBase;
import com.example.user.service.PublicMaterialService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@RestController
@RequestMapping("/material/public")
public class PublicMaterialController {
        @Autowired
        PublicMaterialService publicMaterialService;

        @RequestMapping(value="/querybycate")
         void queryByCate(String category, HttpServletResponse response)
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
        void queryByPid(String pid, HttpServletResponse response) throws IOException {
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
            //检查图片后缀
            String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
            //检查MD5值
            String md5 = org.springframework.util.DigestUtils.md5DigestAsHex(file.getInputStream());
            if (file == null) {
                return "no picture";
            }
            if (category == null){
                return "category not chosen";
            }
            if (!"jpg,png".toUpperCase().contains(suffix.toUpperCase())) {
                return "not a picture like '.jpg' or '.png'";
            }
            if (publicMaterialService.queryByMD5(md5) != null){
                return "picture already exist";
            }
            return publicMaterialService.add(file, category) == 1 ? "success" : "failed";
        }

        @RequestMapping("/delbypid")
        String delByPid(String pid) {
            return publicMaterialService.delByPid(pid) == 1 ? "success" : "failed";
        }
}
