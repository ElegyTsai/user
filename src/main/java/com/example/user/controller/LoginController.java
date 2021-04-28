package com.example.user.controller;

import com.example.user.model.Login;
import com.example.user.model.LoginBase;
import com.example.user.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    LoginService loginService;

    @RequestMapping("/querybyid")
    @ResponseBody
    public LoginBase queryById(@RequestBody String id){
        return loginService.queryById(id);
    }

    @RequestMapping("/")
    public List<LoginBase> queryAll() {
        return loginService.queryAll();
    }

    @RequestMapping("/querybytele")
    public LoginBase queryByTelephone(String telephone) {
        return loginService.queryByTelephone(telephone);
    }

    @RequestMapping("/add")
    public String add(Login login) {
        return loginService.add(login) == 1 ? "success" : "failed";
    }

    @RequestMapping("/updatebyid")
    public String updateById(Login login) {
        return loginService.updateById(login) == 1 ? "success" : "failed";
    }

    @RequestMapping("/updatebytele")
    public String updateByTelephone(Login login) {
        return loginService.updateByTelephone(login) == 1 ? "success" : "failed";
    }

    @RequestMapping("/delbyid")
    public String delById(String id) {
        return loginService.delById(id) == 1 ? "success" : "failed";
    }

    @RequestMapping("/delbytele")
    public String delByTelephone(String telephone) {
        return loginService.delByTelephone(telephone) == 1 ? "success" : "failed";
    }
}
