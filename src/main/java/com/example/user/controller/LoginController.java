package com.example.user.controller;

import com.example.user.entity.Login;
import com.example.user.mapper.LoginMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    LoginMapper loginMapper;

    @RequestMapping("/querybyid")
    Login queryById(String id){
        return loginMapper.queryById(id);
    }

    @RequestMapping("/")
    List<Login> queryAll() {
        return loginMapper.queryAll();
    }

    @RequestMapping("/querybytele")
    Login queryByTelephone(String telephone) {
        return loginMapper.queryByTelephone(telephone);
    }

    @RequestMapping("/add")
    String add(Login login) {
        return loginMapper.add(login) == 1 ? "success" : "failed";
    }

    @RequestMapping("/updatebyid")
    String updateById(Login login) {
        return loginMapper.updateById(login) == 1 ? "success" : "failed";
    }

    @RequestMapping("/updatebytele")
    String updateByTelephone(Login login) {
        return loginMapper.updateByTelephone(login) == 1 ? "success" : "failed";
    }

    @RequestMapping("/delbyid")
    String delById(String id) {
        return loginMapper.delById(id) == 1 ? "success" : "failed";
    }

    @RequestMapping("/delbytele")
    String delByTelephone(String telephone) {
        return loginMapper.delByTelephone(telephone) == 1 ? "success" : "failed";
    }

}
