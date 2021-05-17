package com.example.user.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.user.model.UserPictureBase;
import com.example.user.service.UserPictureService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@RestController
@RequestMapping("/api/picture")
public class UserPictureController {
    @Autowired
    UserPictureService userPictureService;

    @RequestMapping(value="/querybyid")
    void queryById(String id, HttpServletResponse response)
            throws IOException {
        List<UserPictureBase> userPictureBases = userPictureService.queryById(id);
        String json = "";
        ObjectMapper objectMapper = new ObjectMapper ();
        json = objectMapper.writeValueAsString (userPictureBases); //java对象转换为json数据
        PrintWriter writer = response.getWriter ();
        writer.print (json);
        writer.flush ();
        writer.close ();
    }

    @RequestMapping(value = "/querybypid")
    void queryByPid(String pid, HttpServletResponse response) throws IOException {
        String json = userPictureService.queryByPid(pid);
        PrintWriter writer = response.getWriter();
        writer.print(json);
        writer.flush();
        writer.close();
    }

    @RequestMapping(value = "/save")
    String save(@RequestBody JSONObject jsonObject) throws IOException {
        JSONObject file = jsonObject.getJSONObject("file");
        byte[] thumbnail = jsonObject.getBytes("thumbnail");
        String id = jsonObject.getString("id");
        String pid = jsonObject.getString("pid");

        return userPictureService.save(file, thumbnail, id, pid) == 1? "success": "fail";
    }

    @RequestMapping(value = "delbypid")
    String delByPid(String pid){
        return userPictureService.delByPid(pid)==1? "success": "fail";
    }
}
